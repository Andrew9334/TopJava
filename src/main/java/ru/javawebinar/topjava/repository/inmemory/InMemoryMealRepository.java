package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед User's 2", 500), 2);
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин User's 2", 410), 2);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Map<Integer, Meal> meals = repository.computeIfAbsent(userId, id -> new ConcurrentHashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meals.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return meals.computeIfPresent(meal.getId(), (idMeal, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals != null && meals.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return filter(userId, meal -> true);
    }

    @Override
    public List<Meal> filterByDate(LocalDate startDate, LocalDate endDate, int userId) {
        return filter(userId, meal -> DateTimeUtil.isBetweenHalfOpen(meal.getDate(), startDate, endDate));
    }

    private List<Meal> filter(int userId, Predicate<Meal> predicate) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals
                .values()
                .stream()
                .filter(predicate)
                .sorted(((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime())))
                .collect(Collectors.toList());
    }
}


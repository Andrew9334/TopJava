package ru.javawebinar.topjava.MealDao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoSaveToMemory implements MealDao {
    private final Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger(0);

    public MealDaoSaveToMemory() {
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mealMap.values());
    }

    @Override
    public Meal getById(int id) {
        return mealMap.get(id);
    }

    @Override
    public Meal create(Meal meal) {
        if (meal.isNew()) {
            meal.setId(id.incrementAndGet());
        }
        return mealMap.put(meal.getId(), meal);
    }

    @Override
    public void update(int id, Meal meal) {
        mealMap.replace(meal.getId(), meal);
    }

    @Override
    public void delete(Integer id) {
        if (id != null) {
            mealMap.remove(id);
        }
    }
}

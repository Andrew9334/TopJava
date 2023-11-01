package ru.javawebinar.topjava.Meal;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDAOLogic implements MealDAO {

    private Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();
    public MealDAOLogic() {
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }
    private AtomicInteger id = new AtomicInteger(1000);
    @Override
    public List<Meal> getAll(){
        return new ArrayList<Meal>(mealMap.values());
    }

    @Override
    public Meal getMealById(int mealId) {
        return mealMap.get(mealId);
    }

    @Override
    public Meal save(Meal meal) {
        if (isNewMeal(meal.getId())) {
            meal.setId(getId());
            return mealMap.put(meal.getId(), meal);
        }
        return mealMap.replace(meal.getId(), meal);
    }

    @Override
    public void delete(int mealId) {
        mealMap.remove(mealId);
    }

    public int getId(){
        return id.getAndIncrement();
    }

    private boolean isNewMeal(int id) {
        return id == 0;
    }
}

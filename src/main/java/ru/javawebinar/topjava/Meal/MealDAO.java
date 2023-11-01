package ru.javawebinar.topjava.Meal;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDAO {
    List<Meal> getAll();
    Meal getMealById(int mealId);
    Meal save (Meal meal);
    void delete(int mealId);
//    void update(int id, LocalDateTime localDateTime, String description, int calories);
}


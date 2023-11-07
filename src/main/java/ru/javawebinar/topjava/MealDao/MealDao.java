package ru.javawebinar.topjava.MealDao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    List<Meal> getAll();

    Meal getById(int id);

    Meal create(Meal meal);

    void update(Meal meal);

    void delete(int id);
}


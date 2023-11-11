package ru.javawebinar.topjava.MealDao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    List<Meal> getAll();

    Meal getById(int id);

    Meal create(Meal meal);

    void update(int id, Meal meal);

    void delete(Integer id);
}


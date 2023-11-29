package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository {
    // null if not found, when updated
    Meal save(Meal meal, int userId);

    // null if not found, when updated
    boolean delete(int id, int userId);

    // null if not found, when updated
    Meal get(int id, int userId);

    // null if not found, when updated
    List<Meal> getAll(int userId);

    List<Meal> filteredByDateAndByTime(LocalDate startDate, LocalDate endDate, int userId);
}

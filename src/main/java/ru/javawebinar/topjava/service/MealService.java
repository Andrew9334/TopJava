package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    @Autowired
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public Meal get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public List<Meal> getAll(int userId) {
//        return repository.getAll(userId);
        return repository.getAll(userId)
                .stream()
                .filter(meal -> meal.getUserId() == userId)
                .collect(Collectors.toList());
    }

    public void update(Meal meal) {
        checkNotFoundWithId(repository.save(meal), meal.getUserId());
    }
}
package ru.javawebinar.topjava.web.meal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll() {
        int userId = authUserId();
        log.info("getAll");
        return MealsUtil.getTos(service.getAll(userId), authUserCaloriesPerDay());
    }

    public Meal get(int id) {
        int userId = authUserId();
        log.info("get {}", id);
        return service.get(id, userId);
    }

    public Meal create(Meal meal) {
        int userId = authUserId();
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, userId);
    }

    public void update(Meal meal, int id) {
        int userId = authUserId();
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(meal, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete meal with id {}", id);
        service.delete(id, userId);
    }

    public List<MealTo> getByDateAndTime(LocalDate startDate, LocalTime startTime,
                                         LocalDate endDate, LocalTime endTime) {
        LocalDate sD = startDate == null ? LocalDate.MIN : startDate;
        LocalDate eD = endDate == null ? LocalDate.MAX : endDate;
        LocalTime sT = startTime == null ? LocalTime.MIN : startTime;
        LocalTime eT = endTime == null ? LocalTime.MAX : endTime;

        int userId = SecurityUtil.authUserId();

        return MealsUtil.getFilteredTos(service.filterByDate(sD, eD, userId),
                SecurityUtil.authUserCaloriesPerDay(), sT, eT);
    }
}
package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.Meal.MealDao;
import ru.javawebinar.topjava.Meal.MealDaoSaveToMemory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final String LINK_ADD_EDIT = "/meal.jsp";
    private static final String LINK_GET_ALL = "/meals.jsp";
    private static final int CALORIES_PER_DAY = 2000;
    private MealDao mealDaoSaveToMemory;

    @Override
    public void init() throws ServletException {
        mealDaoSaveToMemory = new MealDaoSaveToMemory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String path;
        int id;
        Meal meal;
        action = action == null ? "" : action;

        switch (action) {
            case "create":
                log.debug("Create meal");
                meal = new Meal(null, null, CALORIES_PER_DAY);
                req.setAttribute("meal", meal);
                path = LINK_ADD_EDIT;
                break;
            case "update":
                log.debug("Update meal");
                id = Integer.parseInt(req.getParameter("id"));
                meal = mealDaoSaveToMemory.getById(id);
                req.setAttribute("meal", meal);
                path = LINK_ADD_EDIT;
                break;
            case "delete":
                log.debug("delete meal");
                id = Integer.parseInt(req.getParameter("id"));
                mealDaoSaveToMemory.delete(id);
                resp.sendRedirect("meals");
                return;
            default:
                List<MealTo> list = MealsUtil.filteredByStreams(mealDaoSaveToMemory.getAll(),
                        LocalTime.MIN,
                        LocalTime.MAX,
                        CALORIES_PER_DAY);
                req.setAttribute("meals", list);
                path = LINK_GET_ALL;
        }
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.debug("add or update Meal");
        MealDao mealDaoSaveToMemory = new MealDaoSaveToMemory();
        req.setCharacterEncoding("UTF-8");
        int id = 0;
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("dateTime"), TimeUtil.formatter);
        Meal meal = new Meal(id, localDateTime, description, calories);
        if (Integer.parseInt(req.getParameter("id")) == id) {
            mealDaoSaveToMemory.update(meal);
        } else {
            mealDaoSaveToMemory.create(meal);
        }
        resp.sendRedirect("meals");
    }
}
package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.mealdao.MealDao;
import ru.javawebinar.topjava.mealdao.MealDaoSaveToMemory;
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
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final String LINK_ADD_EDIT = "/meal.jsp";
    private static final String LINK_GET_ALL = "/meals.jsp";
    private static final int CALORIES_PER_DAY = 2000;
    private MealDao mealDao;

    @Override
    public void init() {
        mealDao = new MealDaoSaveToMemory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String path;
        int id;
        Meal meal;
        action = action == null ? "" : action;

        switch (action) {
            case "create":
                log.debug("Create meal");
                meal = new Meal(null, null, 0);
                req.setAttribute("meal", meal);
                path = LINK_ADD_EDIT;
                break;
            case "update":
                log.debug("Update meal");
                id = getId(req);
                meal = mealDao.getById(id);
                req.setAttribute("meal", meal);
                path = LINK_ADD_EDIT;
                break;
            case "delete":
                log.debug("delete meal");
                id = getId(req);
                mealDao.delete(id);
                resp.sendRedirect("meals");
                return;
            default:
                log.debug("redirect to meals");
                List<MealTo> list = MealsUtil.filteredByStreams(mealDao.getAll(),
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
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("dateTime"), TimeUtil.formatter);

        if (id.isEmpty()) {
            Meal meal = new Meal(null, localDateTime, description, calories);
            mealDao.create(meal);
        } else {
            Meal meal = new Meal(getId(req), localDateTime, description, calories);
            mealDao.update(meal);
        }
        resp.sendRedirect("meals");
    }

    private int getId(HttpServletRequest request) {
        String nonNullId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(nonNullId);
    }
}
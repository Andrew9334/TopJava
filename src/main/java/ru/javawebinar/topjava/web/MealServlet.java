package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.Meal.MealDAOLogic;
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
    private MealDAOLogic mealDAOLogic;
    private static final String linkAddEdit = "/meal.jsp";
    private static final String linkGetAll = "/meals.jsp";

    @Override
    public void init() throws ServletException {
        super.init();
        mealDAOLogic = new MealDAOLogic();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String path;
        action = action == null ? linkGetAll : linkAddEdit;
        int id;
        Meal meal;

        switch (action.toLowerCase()) {
            case ("save"):
                meal = new Meal(null, null, 0);
                req.setAttribute("meal", meal);
                path = linkAddEdit;
                break;
            case ("update"):
                id = Integer.parseInt(req.getParameter("id"));
                meal = mealDAOLogic.getMealById(id);
                req.setAttribute("meal", meal);
                path = linkAddEdit;
                break;
            case ("delete"):
                id = Integer.parseInt(req.getParameter("id"));
                mealDAOLogic.delete(id);
                resp.sendRedirect("meals");
            default:
                List<MealTo> list = MealsUtil.filteredByStreams(mealDAOLogic.getAll(),
                        LocalTime.MIN,
                        LocalTime.MAX,
                        2000);
                req.setAttribute("meals", list);
                path = linkGetAll;

        }
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.debug("add or update Meal");
        req.setCharacterEncoding("UTF-8");
        int id = 0;
        String description = req.getParameter("Description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        LocalDateTime localDateTime = TimeUtil.parseLocalDateTime(req.getParameter("dateTime"));
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            Meal meal = new Meal(id, localDateTime, description, calories);
            mealDAOLogic.save(meal);
        }
        resp.sendRedirect("meals");
    }
}
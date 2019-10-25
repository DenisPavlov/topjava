package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.InMemoryMealService;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private MealService mealService;

    @Override
    public void init() throws ServletException {
        super.init();
        mealService = new InMemoryMealService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("get all meals");

        String action = request.getParameter("action");

        if (action != null && action.equals("delete")) {
            String id = request.getParameter("id");
            mealService.deleteById(Integer.parseInt(id));
        }
        if (action != null && action.equals("update")) {
            String id = request.getParameter("id");
            Meal updated = mealService.getById(Integer.parseInt(id));
            request.setAttribute("updated", updated);
            request.getRequestDispatcher("/meal.jsp").forward(request, response);
        }
        if (action != null && action.equals("add")) {
            Meal meal = mealService.create();
            request.setAttribute("updated", meal);
            request.getRequestDispatcher("/meal.jsp").forward(request, response);
        }

        List<MealTo> mealTos = MealsUtil.getWithExcess(mealService.getAll(), DEFAULT_CALORIES_PER_DATE);
        request.setAttribute("meals", mealTos);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String strId = req.getParameter("id");
        Integer id = strId == null ? null : Integer.parseInt(strId);

        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("date"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        Meal meal = mealService.getById(id);
        meal.setDateTime(dateTime);
        meal.setDescription(description);
        meal.setCalories(calories);
        mealService.update(meal);

        resp.sendRedirect("meals");
    }
}

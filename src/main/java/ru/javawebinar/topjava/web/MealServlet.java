package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDaoMemoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Suntey on 25.03.2017.
 */
public class MealServlet extends HttpServlet {


    private MealService mealService = new MealServiceImpl(new MealDaoMemoryImpl());
    private static String LIST_VIEW = "meals.jsp";
    private static String ADD_EDIT = "/addingMeal.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String forward=LIST_VIEW;
        List<MealWithExceed> mealWithExceeds;
        if (action == null){
            mealWithExceeds = MealsUtil.getFilteredWithExceeded(mealService.listMeals(), null, null, 2000);
            request.setAttribute("mealList", mealWithExceeds);
            request.getRequestDispatcher(forward).forward(request, response);
        }
        else if ("delete".equals(action)){
            int mealId = Integer.parseInt(request.getParameter("mealID"));
            mealService.delete(mealId);
            response.sendRedirect("meals");
        }
        else if ("edit".equals(action)){
            forward = ADD_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealID"));
            Meal meal = mealService.getById(mealId);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher(forward).forward(request, response);
        }
        else if ("add".equals(action)){
            forward = ADD_EDIT;
            Meal meal = new Meal(LocalDateTime.now().withSecond(0).withNano(0), "название блюда", 0);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher(forward).forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(LocalDateTime.parse(req.getParameter("date")), req.getParameter("description"),
                Integer.parseInt(req.getParameter("calories")));
        int id = Integer.parseInt(req.getParameter("id"));

        if (id == 0 ){
            mealService.create(meal);
        }
        else{
            meal.setId(id);
            mealService.update(meal);
        }

        List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(mealService.listMeals(), null, null, 2000);

        req.setAttribute("mealList", mealWithExceeds);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}

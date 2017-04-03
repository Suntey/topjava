package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;


    public Meal save(int userId, Meal meal){
        LOG.info("safe "+meal);
        return service.save(userId, meal);
    }

    public void delete(int userId, int id){
        LOG.info("delete "+id);
        service.delete(userId, id);
    }

    public Meal get(int userId, int id){
        LOG.info("get "+id);
        return service.get(userId, id);
    }

    private List<Meal> getAll(){
        LOG.info("getAll");
        return service.getAll(AuthorizedUser.id());
    }
    public List<MealWithExceed> getAllWithExceed(){
        LOG.info("get all without parametrs");
        return MealsUtil.getWithExceeded(getAll(), AuthorizedUser.getCaloriesPerDay());
    }
    public synchronized List<MealWithExceed> getFilteredByDateAndTime(HttpServletRequest request){
        LOG.info("getAllFiltered");
        String startD = request.getParameter("startDate");
        String endD = request.getParameter("endDate");
        String startT = request.getParameter("startTime");
        String endT = request.getParameter("endTime");

        LocalDate startDate = (startD == null || startD.isEmpty())? LocalDate.MIN : LocalDate.parse(startD);
        LocalDate endDate = (endD == null || endD.isEmpty())? LocalDate.MAX : LocalDate.parse(endD);
        LocalTime startTime = (startT == null || startT.isEmpty())? LocalTime.MIN : LocalTime.parse(startT);
        LocalTime endTime = (endT == null || endT.isEmpty())? LocalTime.MAX.withNano(0).withSecond(0) : LocalTime.parse(endT);

        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.setAttribute("startTime", startTime);
        request.setAttribute("endTime", endTime);

        return MealsUtil.getFilteredByTime(service.getFilteredByDate(AuthorizedUser.id(), startDate, endDate),startTime,endTime, AuthorizedUser.getCaloriesPerDay());
    }
}
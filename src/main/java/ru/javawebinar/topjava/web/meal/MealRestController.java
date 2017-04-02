package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
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

    public List<Meal> getAll(int userId){
        LOG.info("getAll");
        return service.getAll(userId);
    }
    public List<MealWithExceed> getAllWithExceed(int userId, int calories){
        LOG.info("get all without parametrs");
        return MealsUtil.getWithExceeded(getAll(userId), calories);
    }
    public List<MealWithExceed> getFilteredByDateAndTime(int userId, String startD, String endD, String startT, String endT, int calories){
        LOG.info("getAllFiltered");
        LocalDate startDate = (startD == null || startD.isEmpty())? LocalDate.MIN : LocalDate.parse(startD, DateTimeUtil.DATE_FORMATTER);
        LocalDate endDate = (endD == null || endD.isEmpty())? LocalDate.MAX : LocalDate.parse(endD,DateTimeUtil.DATE_FORMATTER);
        LocalTime startTime = (startT == null || startT.isEmpty())? LocalTime.MIN : LocalTime.parse(startT,DateTimeUtil.TIME_FORMATTER);
        LocalTime endTime = (endT == null || endT.isEmpty())? LocalTime.MAX : LocalTime.parse(endT,DateTimeUtil.TIME_FORMATTER);
        return MealsUtil.getFilteredByDateAndTime(getAll(userId), startDate, endDate, LocalTime.MIN, LocalTime.MAX, calories);
    }
}
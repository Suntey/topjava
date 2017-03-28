package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;


/**
 * Created by Suntey on 26.03.2017.
 */
public class MealServiceImpl implements MealService {
    private MealDao mealDao;

    public MealServiceImpl(MealDao mealDao) {
        this.mealDao = mealDao;
    }

    @Override
    public void create(Meal meal) { mealDao.create(meal);}

    @Override
    public void update(Meal meal) { mealDao.update(meal);}

    @Override
    public void delete(int id) { mealDao.delete(id);}

    @Override
    public Meal getById(int id) { return mealDao.getMealById(id);}

    @Override
    public List<Meal> listMeals() {return mealDao.listMeals();}

    public void setMealDao(MealDao mealDao) { this.mealDao = mealDao; }
}




package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

/**
 * Created by Suntey on 27.03.2017.
 */
public interface MealDao {
    void create(Meal meal);

    void update(Meal user);

    void delete(int id);

    Meal getMealById(int id);

    List<Meal> listMeals();

}


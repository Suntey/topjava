package ru.javawebinar.topjava.service;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by Suntey on 27.03.2017.
 */
public interface MealService {
    void create(Meal meal);

    void update(Meal meal);

    void delete(int id);

    Meal getById(int id);

    List<Meal> listMeals();
}

package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.mockdb.MockDb;
import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suntey on 27.03.2017.
 */
public class MealDaoMemoryImpl implements MealDao {
    MockDb dataBase = MockDb.getInstance();
    @Override
    public void create(Meal meal) {
        int id = dataBase.generateID();
        meal.setId(id);
        dataBase.concurrentMap.put(id, meal);
    }

    @Override
    public void update(Meal user) {
        dataBase.concurrentMap.put(user.getId(), user);
    }

    @Override
    public void delete(int id) {
        Meal meal = getById(id);
        if (meal!=null){ dataBase.concurrentMap.remove(id, meal);}
    }

    @Override
    public Meal getById(int id) {
        return dataBase.concurrentMap.get(id);
    }

    @Override
    public List<Meal> list() {
        return new ArrayList<>(dataBase.concurrentMap.values());
    }
}

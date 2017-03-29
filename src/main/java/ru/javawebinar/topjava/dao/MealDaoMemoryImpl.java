package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Suntey on 27.03.2017.
 */
public class MealDaoMemoryImpl implements MealDao {
    public static ConcurrentMap<Integer, Meal> concurrentMap = new ConcurrentHashMap<>();
    private final static AtomicInteger id = new AtomicInteger(1);
    private static int generateID(){
        return id.getAndIncrement();
    }

    static{
        Meal meal = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        meal.setId(generateID());
        concurrentMap.put(meal.getId(),meal);

        meal = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
        meal.setId(generateID());
        concurrentMap.put(meal.getId(),meal);

        meal = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
        meal.setId(generateID());
        concurrentMap.put(meal.getId(),meal);

        meal = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
        meal.setId(generateID());
        concurrentMap.put(meal.getId(),meal);

        meal = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
        meal.setId(generateID());
        concurrentMap.put(meal.getId(),meal);

        meal = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
        meal.setId(generateID());
        concurrentMap.put(meal.getId(),meal);
    }

//    private static class ThreadsafeSingletonHelper{
//        private static final MealDaoMemoryImpl INSTANCE = new MealDaoMemoryImpl();
//    }
//    public static MealDaoMemoryImpl getInstance(){
//        return MealDaoMemoryImpl.ThreadsafeSingletonHelper.INSTANCE;
//    }

    @Override
    public void create(Meal meal) {
        int id = generateID();
        meal.setId(id);
        concurrentMap.put(id, meal);
    }

    @Override
    public void update(Meal user) {
        concurrentMap.put(user.getId(), user);
    }

    @Override
    public void delete(int id) {
        Meal meal = getById(id);
        if (meal!=null){ concurrentMap.remove(id, meal);}
    }

    @Override
    public Meal getById(int id) {
        return concurrentMap.get(id);
    }

    @Override
    public List<Meal> list() {
        return new ArrayList<>(concurrentMap.values());
    }
}

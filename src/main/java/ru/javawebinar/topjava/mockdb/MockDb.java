package ru.javawebinar.topjava.mockdb;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by Suntey on 27.03.2017.
 */
public class MockDb {
    public static ConcurrentMap<Integer, Meal> concurrentMap = new ConcurrentHashMap<>();
    private final static AtomicInteger id = new AtomicInteger(1);
    static {
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

    public static int generateID(){
        return id.getAndIncrement();
    }

    private MockDb() {}

    private static class ThreadsafeSingletonHelper{
        private static final MockDb INSTANCE = new MockDb();
    }
    public static MockDb getInstance(){
        return ThreadsafeSingletonHelper.INSTANCE;
    }
}

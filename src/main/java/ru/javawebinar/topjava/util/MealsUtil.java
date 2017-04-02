package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class MealsUtil {
    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static void main(String[] args) {
        List<MealWithExceed> mealsWithExceeded = getFilteredByTime(MEALS, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsWithExceeded.forEach(System.out::println);

    }

// ----------------------------Meal-----------------------------
    public static List<Meal> getFilteredByDate(Collection<Meal> meals, LocalDate startDate, LocalDate endDate){
    return getSortedByDateAndTime(meals.stream()
            .filter(meal -> DateTimeUtil.isBetweenDate(meal.getDate(), startDate, endDate))
            .collect(Collectors.toList()));
}
    public static List<Meal> getSortedByDateAndTime(Collection<Meal> meals){
        return meals.stream().sorted(Comparator.comparing(Meal::getDate).thenComparing(Comparator.comparing(Meal::getTime)).reversed())
                .collect(Collectors.toList());
    }


//    ------------------------MealWithExceed---------------------

    public static List<MealWithExceed> getWithExceeded(Collection<Meal> meals, int caloriesPerDay) {
    return getFilteredByTime(meals, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
}
    public static List<MealWithExceed> getFilteredByTime(Collection<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
                );
        return meals.stream()
                .filter(meal -> DateTimeUtil.isBetweenTime(meal.getTime(), startTime, endTime))
                .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }




    public static List<MealWithExceed> getFilteredByDateAndTime(List<Meal> meals, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int calories){

        List<Meal> filteredByDate = meals.stream().
                filter(meal->DateTimeUtil.isBetweenDate(meal.getDateTime().toLocalDate(), startDate, endDate)).
                collect(Collectors.toList());

        return getFilteredByTime(filteredByDate, startTime, endTime, calories);
    }

    public static MealWithExceed createWithExceed(Meal meal, boolean exceeded) {
        return new MealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded);
    }




}
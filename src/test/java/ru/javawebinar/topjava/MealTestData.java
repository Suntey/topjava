package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 2;

    public static final Meal BREAKFAST = new Meal(MEAL_ID, LocalDateTime.of(2017, Month.MARCH, 1, 10, 00), "Завтрак", 500);
    public static final Meal LUNCH = new Meal(MEAL_ID + 1, LocalDateTime.of(2017, Month.MARCH, 1, 13, 30), "Обед", 1000);
    public static final Meal SUPPER = new Meal(MEAL_ID + 2, LocalDateTime.of(2017, Month.MARCH, 1, 20, 00), "Ужин", 500);
    public static final Meal ANOTHERDAY = new Meal(MEAL_ID + 3, LocalDateTime.of(2017, Month.MARCH, 2, 10, 00), "Завтрак1", 500);

    public static final ModelMatcher<Meal> MEAL_MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual || Objects.equals(expected.toString(), actual.toString()));

}

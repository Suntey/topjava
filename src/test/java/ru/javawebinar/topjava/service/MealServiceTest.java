package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;

import java.time.LocalDateTime;
import java.util.Arrays;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by Suntey on 11.04.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {
    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void get() throws Exception {
        Meal meal = service.get(MEAL_ID, USER_ID);
        MEAL_MATCHER.assertEquals(BREAKFAST, meal);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MEAL_ID, USER_ID);
        System.out.println(service.getAll(USER_ID));
        MEAL_MATCHER.assertCollectionEquals(Arrays.asList(SUPPER, LUNCH), service.getAll(USER_ID));
    }

    @Test
    public void getBetweenDates() throws Exception {

    }

    @Test
    public void getBetweenDateTimes() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
        MEAL_MATCHER.assertCollectionEquals(Arrays.asList(SUPPER, LUNCH, BREAKFAST), service.getAll(USER_ID));
    }

    @Test
    public void update() throws Exception {
        Meal updatedMeal = new Meal(BREAKFAST);
        updatedMeal.setDescription("UpdatedDescription");
        service.update(updatedMeal, USER_ID);
        MEAL_MATCHER.assertEquals(updatedMeal, service.get(MEAL_ID, USER_ID));
    }

    @Test
    public void save() throws Exception {
        Meal newMeal = new Meal(LocalDateTime.now(), "newMeal", 1000);
        Meal created = service.save(newMeal, USER_ID);
        newMeal.setId(created.getId());
        MEAL_MATCHER.assertCollectionEquals(Arrays.asList(newMeal, SUPPER, LUNCH, BREAKFAST), service.getAll(USER_ID));
    }

}

package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL1_ID, USER_ID);
        assertMatch(meal, MEAL1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void get_adminMeal_notFoundException() {
        service.get(MEAL1_ID, ADMIN_ID);
    }

    @Test
    public void delete() {
        service.delete(MEAL1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), MEALS);
    }

    @Test(expected = NotFoundException.class)
    public void delete_adminMeal_notFoundException() {
        service.delete(MEAL1_ID, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(1, USER_ID);
    }

    @Test
    public void getBetweenDates() {
        List<Meal> betweenDates = service.getBetweenDates(LocalDate.of(2015, Month.MAY, 30),
                LocalDate.of(2015, Month.MAY, 30), USER_ID);

        assertMatch(betweenDates, MEAL1, MEAL2, MEAL3);
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> betweenDateTimes = service.getBetweenDateTimes(LocalDateTime.of(2015, Month.MAY, 30, 9, 0),
                LocalDateTime.of(2015, Month.MAY, 30, 15, 0), USER_ID);

        assertMatch(betweenDateTimes, MEAL1, MEAL2);
    }

    @Test
    public void getAll() {
        List<Meal> meals = service.getAll(USER_ID);
        List<Meal> expected = new ArrayList<>();
        expected.add(MEAL1);
        expected.addAll(MEALS);
        assertMatch(meals, expected);
    }

    @Test
    public void update() {
        Meal updated = new Meal(MEAL1);
        updated.setDescription("Updated description");
        service.update(updated, USER_ID);
        assertMatch(service.get(updated.getId(), USER_ID), updated);
    }

    @Test
    public void create() {
        Meal new_meal = new Meal(null, LocalDateTime.now(), "New", 1000);
        Meal created = service.create(new_meal, USER_ID);
        service.create(new_meal, USER_ID);
        new_meal.setId(created.getId());
        assertMatch(new_meal, created);
    }
}

package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

public class MealRestController {

    private MealService service;

    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    public List<MealTo> getAll(){
        log.info("getAll");
        return MealsUtil.getWithExcess(service.getAll(authUserId()), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Meal get(int id) {
        log.info("get");
        return service.get(id, authUserId());
    }

    public Meal create(Meal meal) {
        log.info("create");
        return service.create(meal, authUserId());
    }

    public void delete(int id) {
        log.info("delete");
        service.delete(id, authUserId());
    }

    public List<MealTo> getBetween(LocalDateTime start, LocalDateTime end ) {
        log.info("getBetween");
        return MealsUtil.getFilteredWithExcess(service.getAll(authUserId()), MealsUtil.DEFAULT_CALORIES_PER_DAY, start, end);
    }

}
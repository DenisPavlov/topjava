package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.exceptions.MealAlreadyExists;
import ru.javawebinar.topjava.exceptions.MealNotExists;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryMealService implements MealService {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealService.class);

    private MealRepository mealRepository = new InMemoryMealRepository();

    @Override
    public Meal create() {
        Meal meal = new Meal();
        return mealRepository.save(meal);
    }

    @Override
    public Meal getById(int id) {
        return mealRepository.getById(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mealRepository.getAll());
    }

    @Override
    public Meal update(Meal meal) {
        Meal actual = mealRepository.getById(meal.getId());

        if (actual == null) {
            String message = "Meal with id " + meal.getId() + " not exists";
            log.warn(message);
            throw new MealNotExists(message);
        }
        return mealRepository.save(meal);
    }

    @Override
    public Meal deleteById(int id) {
        Meal current = mealRepository.getById(id);
        return mealRepository.delete(current);
    }
}

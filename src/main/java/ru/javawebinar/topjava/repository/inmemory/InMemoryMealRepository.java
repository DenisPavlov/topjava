package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryMealRepository implements MealRepository {

    private final static Map<Integer, Meal> meals = new ConcurrentHashMap<>();

    @Override
    public Meal save(Meal meal) {
        return meals.put(meal.getId(), meal);
    }

    @Override
    public Collection<Meal> getAll() {
        return meals.values();
    }

    @Override
    public Meal getById(int id) {
        return meals.get(id);
    }

    @Override
    public Meal delete(Meal meal) {
        return meals.remove(meal.getId());
    }
}

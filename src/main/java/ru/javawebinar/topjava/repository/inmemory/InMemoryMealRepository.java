package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealRepository implements MealRepository {

    private final static Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private final static AtomicInteger counter = new AtomicInteger();

    {
        MealsUtil.createMeals().forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) meal.setId(counter.incrementAndGet());
        meals.put(meal.getId(), meal);
        return meals.get(meal.getId());
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

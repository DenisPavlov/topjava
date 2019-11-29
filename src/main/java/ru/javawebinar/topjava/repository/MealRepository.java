package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealRepository {
    Meal save(Meal meal, long userId);

    // false if not found
    boolean delete(int id, long userId);

    // null if not found
    Meal get(int id, long userId);

    Collection<Meal> getAll(long userId);
}

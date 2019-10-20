package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealRepository {
    Meal save(Meal meal);
    Collection<Meal> getAll();
    Meal getById(int id);
    Meal delete(Meal meal);
}

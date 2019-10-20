package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {
    Meal create(Meal meal);
    Meal getById(int id);
    List<Meal> getAll();
    Meal update(Meal meal);
    Meal delete(Meal meal);
}

package ru.javawebinar.topjava.exceptions;

public class MealNotExists extends RuntimeException {
    public MealNotExists(String message) {
        super(message);
    }
}

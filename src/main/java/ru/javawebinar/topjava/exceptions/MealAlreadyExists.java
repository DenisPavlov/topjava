package ru.javawebinar.topjava.exceptions;

public class MealAlreadyExists extends RuntimeException {

    public MealAlreadyExists(String message) {
        super(message);
    }
}

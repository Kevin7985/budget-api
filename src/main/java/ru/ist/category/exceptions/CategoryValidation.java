package ru.ist.category.exceptions;

public class CategoryValidation extends RuntimeException {
    public CategoryValidation(String message) {
        super(message);
    }
}

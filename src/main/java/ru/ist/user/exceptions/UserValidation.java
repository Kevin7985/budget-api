package ru.ist.user.exceptions;

public class UserValidation extends RuntimeException {
    public UserValidation(String message) {
        super(message);
    }
}

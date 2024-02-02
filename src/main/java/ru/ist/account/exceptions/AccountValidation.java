package ru.ist.account.exceptions;

public class AccountValidation extends RuntimeException {
    public AccountValidation(String message) {
        super(message);
    }
}

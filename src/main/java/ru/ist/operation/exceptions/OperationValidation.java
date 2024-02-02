package ru.ist.operation.exceptions;

public class OperationValidation extends RuntimeException {
    public OperationValidation(String message) {
        super(message);
    }
}

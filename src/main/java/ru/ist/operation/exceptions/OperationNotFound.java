package ru.ist.operation.exceptions;

public class OperationNotFound extends RuntimeException {
    public OperationNotFound(String message) {
        super(message);
    }
}

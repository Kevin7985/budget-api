package ru.ist.error;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ist.account.exceptions.AccountNotFound;
import ru.ist.account.exceptions.AccountValidation;
import ru.ist.category.exceptions.CategoryNotFound;
import ru.ist.category.exceptions.CategoryValidation;
import ru.ist.error.model.ApiError;
import ru.ist.operation.exceptions.OperationNotFound;
import ru.ist.operation.exceptions.OperationValidation;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({
            AccountValidation.class,
            CategoryValidation.class,
            OperationValidation.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError badRequestHandler(final Exception e) {
        return new ApiError(
                HttpStatus.BAD_REQUEST.name(),
                e.getMessage()
        );
    }

    @ExceptionHandler({
            AccountNotFound.class,
            CategoryNotFound.class,
            OperationNotFound.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError notFoundHandler(final Exception e) {
        return new ApiError(
                HttpStatus.NOT_FOUND.name(),
                e.getMessage()
        );
    }

    @ExceptionHandler({
            DataIntegrityViolationException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError conflictHandler(final Exception e) {
        return new ApiError(
                HttpStatus.CONFLICT.name(),
                e.getMessage()
        );
    }
}

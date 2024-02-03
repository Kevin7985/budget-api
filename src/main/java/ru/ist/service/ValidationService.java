package ru.ist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ist.account.AccountRepository;
import ru.ist.account.exceptions.AccountNotFound;
import ru.ist.account.model.Account;
import ru.ist.category.CategoryRepository;
import ru.ist.category.exceptions.CategoryNotFound;
import ru.ist.category.model.Category;
import ru.ist.operation.OperationRepository;
import ru.ist.operation.exceptions.OperationNotFound;
import ru.ist.operation.model.Operation;
import ru.ist.user.UserRepository;
import ru.ist.user.exceptions.UserNotFound;
import ru.ist.user.model.User;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;
    private final OperationRepository operationRepository;
    private final UserRepository userRepository;

    public Account validateAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFound("Счёт с id = " + id + " не найден")
        );
    }

    public Category validateCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFound("Категория с id = " + id + " не найдена")
        );
    }

    public Operation validateOperation(Long id) {
        return operationRepository.findById(id).orElseThrow(
                () -> new OperationNotFound("Операция с id = " + id + " не найдена")
        );
    }

    public User validateUser(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFound("Пользователь с id = " + id + " не найден")
        );
    }
}

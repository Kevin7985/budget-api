package ru.ist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ist.account.AccountRepository;
import ru.ist.account.exceptions.AccountNotFound;
import ru.ist.account.model.Account;
import ru.ist.category.CategoryRepository;
import ru.ist.category.exceptions.CategoryNotFound;
import ru.ist.category.model.Category;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

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
}

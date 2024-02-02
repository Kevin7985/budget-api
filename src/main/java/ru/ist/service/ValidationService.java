package ru.ist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ist.account.AccountRepository;
import ru.ist.account.exceptions.AccountNotFound;
import ru.ist.account.model.Account;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final AccountRepository accountRepository;

    public Account validateAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFound("Счёт с id = " + id + " не найден")
        );
    }
}

package ru.ist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ist.account.dto.AccountDto;
import ru.ist.account.dto.AccountInputDto;
import ru.ist.account.dto.AccountMapper;
import ru.ist.account.model.Account;

@Service
@RequiredArgsConstructor
public class MapperService {
    private final AccountMapper accountMapper;

    public Account toAccount(AccountInputDto accountInputDto) {
        return accountMapper.toAccount(accountInputDto);
    }

    public AccountDto toAccountDto(Account account) {
        return accountMapper.toAccountDto(account);
    }
}

package ru.ist.account.dto;

import org.springframework.stereotype.Component;
import ru.ist.account.model.Account;

@Component
public class AccountMapper {
    public Account toAccount(AccountInputDto accountInputDto) {
        return new Account(
                null,
                accountInputDto.getTitle(),
                accountInputDto.getDescription(),
                accountInputDto.getValute(),
                accountInputDto.getAmount()
        );
    }

    public AccountDto toAccountDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getTitle(),
                account.getDescription(),
                account.getValute(),
                account.getAmount()
        );
    }
}

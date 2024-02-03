package ru.ist.account.dto;

import org.springframework.stereotype.Component;
import ru.ist.account.model.Account;
import ru.ist.user.dto.UserDto;
import ru.ist.user.model.User;

@Component
public class AccountMapper {
    public Account toAccount(AccountInputDto accountInputDto, User user) {
        return new Account(
                null,
                user,
                accountInputDto.getTitle(),
                accountInputDto.getDescription(),
                accountInputDto.getValute(),
                accountInputDto.getAmount()
        );
    }

    public AccountDto toAccountDto(Account account, UserDto userDto) {
        return new AccountDto(
                account.getId(),
                userDto,
                account.getTitle(),
                account.getDescription(),
                account.getValute(),
                account.getAmount()
        );
    }
}

package ru.ist.account;

import ru.ist.account.dto.AccountDto;
import ru.ist.account.dto.AccountInputDto;
import ru.ist.account.dto.AccountUpdateDto;
import ru.ist.account.model.Valute;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(Long userId, AccountInputDto accountInputDto);

    List<AccountDto> getAccounts(Long userId, List<Valute> valuteList);

    AccountDto getAccountById(Long userId, Long id);

    AccountDto updateAccountById(Long userId, Long id, AccountUpdateDto accountUpdateDto);

    void deleteAccountById(Long userId, Long id);
}

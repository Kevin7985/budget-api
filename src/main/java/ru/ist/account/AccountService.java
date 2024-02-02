package ru.ist.account;

import ru.ist.account.dto.AccountDto;
import ru.ist.account.dto.AccountInputDto;
import ru.ist.account.dto.AccountUpdateDto;
import ru.ist.account.model.Valute;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountInputDto accountInputDto);

    List<AccountDto> getAccounts(List<Valute> valuteList);

    AccountDto getAccountById(Long id);

    AccountDto updateAccountById(Long id, AccountUpdateDto accountUpdateDto);

    void deleteAccountById(Long id);
}

package ru.ist.account;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ist.account.dto.AccountDto;
import ru.ist.account.dto.AccountInputDto;
import ru.ist.account.dto.AccountUpdateDto;
import ru.ist.account.model.Valute;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Validated
@Tag(name = "Accounts", description = "Методы для работы с счетами")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto createAccount(@RequestBody @Valid AccountInputDto accountInputDto) {
        return accountService.createAccount(accountInputDto);
    }

    @GetMapping
    public List<AccountDto> getAccounts(
            @RequestParam(defaultValue = "") List<Valute> valuteList
            ) {
        return accountService.getAccounts(valuteList);
    }

    @GetMapping("/{id}")
    public AccountDto getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PatchMapping("/{id}")
    public AccountDto updateAccount(@PathVariable Long id, @RequestBody AccountUpdateDto accountUpdateDto) {
        return accountService.updateAccountById(id, accountUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccountById(id);
    }
}

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
    public AccountDto createAccount(@RequestHeader("X-Ist-Budget-User-Id") Long userId, @RequestBody @Valid AccountInputDto accountInputDto) {
        return accountService.createAccount(userId, accountInputDto);
    }

    @GetMapping
    public List<AccountDto> getAccounts(
            @RequestHeader("X-Ist-Budget-User-Id") Long userId,
            @RequestParam(defaultValue = "") List<Valute> valutes
            ) {
        return accountService.getAccounts(userId, valutes);
    }

    @GetMapping("/{id}")
    public AccountDto getAccountById(@RequestHeader("X-Ist-Budget-User-Id") Long userId, @PathVariable Long id) {
        return accountService.getAccountById(userId, id);
    }

    @PatchMapping("/{id}")
    public AccountDto updateAccount(@RequestHeader("X-Ist-Budget-User-Id") Long userId, @PathVariable Long id, @RequestBody AccountUpdateDto accountUpdateDto) {
        return accountService.updateAccountById(userId, id, accountUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@RequestHeader("X-Ist-Budget-User-Id") Long userId, @PathVariable Long id) {
        accountService.deleteAccountById(userId, id);
    }
}

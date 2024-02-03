package ru.ist.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ist.account.dto.AccountDto;
import ru.ist.account.dto.AccountInputDto;
import ru.ist.account.dto.AccountUpdateDto;
import ru.ist.account.model.Account;
import ru.ist.account.model.Valute;
import ru.ist.error.exceptions.Forbidden;
import ru.ist.service.MapperService;
import ru.ist.service.ValidationService;
import ru.ist.user.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final MapperService mapperService;
    private final ValidationService validationService;
    private final AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(Long userId, AccountInputDto accountInputDto) {
        User user = validationService.validateUser(userId);
        Account account = accountRepository.save(mapperService.toAccount(accountInputDto, user));

        log.info("Создан новый счёт: " + account);
        return mapperService.toAccountDto(account, user);
    }

    @Override
    public List<AccountDto> getAccounts(Long userId, List<Valute> valuteList) {
        User user = validationService.validateUser(userId);

        if (!valuteList.isEmpty()) {
            log.info("Получена информация о счетах с валютами");
            return accountRepository.findByUser_IdAndValuteIn(userId, valuteList).stream()
                    .map(item -> mapperService.toAccountDto(item, user))
                    .collect(Collectors.toList());
        }

        log.info("Получена информация обо всех счетах");
        return accountRepository.findByUser_Id(userId).stream()
                .map(item -> mapperService.toAccountDto(item, user))
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto getAccountById(Long userId, Long id) {
        User user = validationService.validateUser(userId);
        Account account = validationService.validateAccount(id);

        if (!account.getUser().getId().equals(userId)) {
            throw new Forbidden();
        }

        log.info("Получение информации о счёте: " + account);
        return mapperService.toAccountDto(account, user);
    }

    @Override
    public AccountDto updateAccountById(Long userId, Long id, AccountUpdateDto accountUpdateDto) {
        User user = validationService.validateUser(userId);
        Account account = validationService.validateAccount(id);

        if (!account.getUser().getId().equals(userId)) {
            throw new Forbidden();
        }

        account.setTitle(accountUpdateDto.getTitle() == null ? account.getTitle() : accountUpdateDto.getTitle());
        account.setDescription(accountUpdateDto.getDescription() == null ? account.getDescription() : accountUpdateDto.getDescription());

        log.info("Обновлена информация о счёте: " + account);
        return mapperService.toAccountDto(accountRepository.save(account), user);
    }

    @Override
    public void deleteAccountById(Long userId, Long id) {
        User user = validationService.validateUser(userId);
        Account account = validationService.validateAccount(id);

        if (!account.getUser().getId().equals(userId)) {
            throw new Forbidden();
        }

        log.info("Счёт с id = " + id + " успешно удалён");
        accountRepository.deleteById(id);
    }
}

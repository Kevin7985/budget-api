package ru.ist.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ist.account.dto.AccountDto;
import ru.ist.account.dto.AccountInputDto;
import ru.ist.account.dto.AccountUpdateDto;
import ru.ist.account.model.Account;
import ru.ist.account.model.Valute;
import ru.ist.service.MapperService;
import ru.ist.service.ValidationService;

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
    public AccountDto createAccount(AccountInputDto accountInputDto) {
        Account account = mapperService.toAccount(accountInputDto);

        log.info("Создан новый счёт: " + account);
        return mapperService.toAccountDto(accountRepository.save(account));
    }

    @Override
    public List<AccountDto> getAccounts(List<Valute> valuteList) {
        if (!valuteList.isEmpty()) {
            return accountRepository.findByValuteIn(valuteList).stream()
                    .map(mapperService::toAccountDto)
                    .collect(Collectors.toList());
        }

        return accountRepository.findAll().stream()
                .map(mapperService::toAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = validationService.validateAccount(id);

        log.info("Получение информации о счёте: " + account);
        return mapperService.toAccountDto(account);
    }

    @Override
    public AccountDto updateAccountById(Long id, AccountUpdateDto accountUpdateDto) {
        Account account = validationService.validateAccount(id);

        account.setTitle(accountUpdateDto.getTitle() == null ? account.getTitle() : accountUpdateDto.getTitle());
        account.setDescription(accountUpdateDto.getDescription() == null ? account.getDescription() : accountUpdateDto.getDescription());

        log.info("Обновлена информация о счёте: " + account);
        return mapperService.toAccountDto(accountRepository.save(account));
    }

    @Override
    public void deleteAccountById(Long id) {
        validationService.validateAccount(id);

        log.info("Счёт с id = " + id + " успешно удалён");
        accountRepository.deleteById(id);
    }
}

package ru.ist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ist.account.dto.AccountDto;
import ru.ist.account.dto.AccountInputDto;
import ru.ist.account.dto.AccountMapper;
import ru.ist.account.model.Account;
import ru.ist.category.dto.CategoryDto;
import ru.ist.category.dto.CategoryInputDto;
import ru.ist.category.dto.CategoryMapper;
import ru.ist.category.model.Category;
import ru.ist.operation.dto.OperationDto;
import ru.ist.operation.dto.OperationInputDto;
import ru.ist.operation.dto.OperationMapper;
import ru.ist.operation.model.Operation;

@Service
@RequiredArgsConstructor
public class MapperService {
    private final AccountMapper accountMapper;
    private final CategoryMapper categoryMapper;
    private final OperationMapper operationMapper;

    public Account toAccount(AccountInputDto accountInputDto) {
        return accountMapper.toAccount(accountInputDto);
    }

    public AccountDto toAccountDto(Account account) {
        return accountMapper.toAccountDto(account);
    }

    public Category toCategory(CategoryInputDto categoryInputDto) {
        return categoryMapper.toCategory(categoryInputDto);
    }

    public CategoryDto toCategoryDto(Category category) {
        return categoryMapper.toCategoryDto(category);
    }

    public Operation toOperation(OperationInputDto operationInputDto, Account account, Category category) {
        return operationMapper.toOperation(operationInputDto, account, category);
    }

    public OperationDto toOperationDto(Operation operation) {
        return operationMapper.toOperationDto(
                operation,
                toAccountDto(operation.getAccount()),
                toCategoryDto(operation.getCategory())
        );
    }
}

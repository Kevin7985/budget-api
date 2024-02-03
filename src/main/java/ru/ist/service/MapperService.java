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
import ru.ist.user.dto.UserDto;
import ru.ist.user.dto.UserInputDto;
import ru.ist.user.dto.UserMapper;
import ru.ist.user.model.User;

@Service
@RequiredArgsConstructor
public class MapperService {
    private final AccountMapper accountMapper;
    private final CategoryMapper categoryMapper;
    private final OperationMapper operationMapper;
    private final UserMapper userMapper;

    public Account toAccount(AccountInputDto accountInputDto, User user) {
        return accountMapper.toAccount(accountInputDto, user);
    }

    public AccountDto toAccountDto(Account account) {
        return accountMapper.toAccountDto(account, toUserDto(account.getUser()));
    }

    public Category toCategory(CategoryInputDto categoryInputDto, User user) {
        return categoryMapper.toCategory(categoryInputDto, user);
    }

    public CategoryDto toCategoryDto(Category category) {
        return categoryMapper.toCategoryDto(category, toUserDto(category.getUser()));
    }

    public Operation toOperation(OperationInputDto operationInputDto, User user, Account account, Category category) {
        return operationMapper.toOperation(operationInputDto, user, account, category);
    }

    public OperationDto toOperationDto(Operation operation) {
        return operationMapper.toOperationDto(
                operation,
                toUserDto(operation.getUser()),
                toAccountDto(operation.getAccount()),
                toCategoryDto(operation.getCategory())
        );
    }

    public User toUser(UserInputDto userInputDto) {
        return userMapper.toUser(userInputDto);
    }

    public UserDto toUserDto(User user) {
        return userMapper.toUserDto(user);
    }
}

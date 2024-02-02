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

@Service
@RequiredArgsConstructor
public class MapperService {
    private final AccountMapper accountMapper;
    private final CategoryMapper categoryMapper;

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
}

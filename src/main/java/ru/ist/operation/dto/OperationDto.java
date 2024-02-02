package ru.ist.operation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ist.account.dto.AccountDto;
import ru.ist.category.dto.CategoryDto;
import ru.ist.operation.model.OperationType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OperationDto {
    private final Long id;
    private final AccountDto account;
    private final CategoryDto category;
    private final OperationType operationType;
    private final double amount;
    private final LocalDateTime createdAt;
}

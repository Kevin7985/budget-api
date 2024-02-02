package ru.ist.operation.dto;

import org.springframework.stereotype.Component;
import ru.ist.account.dto.AccountDto;
import ru.ist.account.model.Account;
import ru.ist.category.dto.CategoryDto;
import ru.ist.category.model.Category;
import ru.ist.operation.model.Operation;

import java.time.LocalDateTime;

@Component
public class OperationMapper {
    public Operation toOperation(OperationInputDto operationInputDto, Account account, Category category) {
        return new Operation(
                null,
                account,
                category,
                operationInputDto.getOperationType(),
                operationInputDto.getAmount(),
                LocalDateTime.now()
        );
    }

    public OperationDto toOperationDto(Operation operation, AccountDto accountDto, CategoryDto categoryDto) {
        return new OperationDto(
                operation.getId(),
                accountDto,
                categoryDto,
                operation.getOperationType(),
                operation.getAmount(),
                operation.getCreatedAt()
        );
    }
}

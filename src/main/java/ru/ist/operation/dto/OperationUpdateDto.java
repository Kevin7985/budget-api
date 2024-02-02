package ru.ist.operation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ist.operation.exceptions.OperationValidation;
import ru.ist.operation.model.OperationType;

@Data
public class OperationUpdateDto {
    private final Long accountId;
    private final Long categoryId;
    private final OperationType operationType;
    private final Double amount;

    public OperationUpdateDto(Long accountId, Long categoryId, OperationType operationType, Double amount) {
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.operationType = operationType;

        if (amount != null && amount < 0.01) {
            throw new OperationValidation("Сумма не может быть меньше 0.01 у.е.");
        }

        this.amount = amount;
    }
}

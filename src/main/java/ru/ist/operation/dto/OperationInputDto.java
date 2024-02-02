package ru.ist.operation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ist.operation.model.OperationType;

@Data
@AllArgsConstructor
public class OperationInputDto {
    @NotNull
    private final Long account_id;

    @NotNull
    private final Long category_id;

    private final OperationType operationType;

    @Positive
    private double amount;
}

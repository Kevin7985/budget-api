package ru.ist.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ist.operation.model.OperationType;

@Data
@AllArgsConstructor
public class CategoryInputDto {
    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private final String title;

    private final OperationType operationType;
}

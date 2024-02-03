package ru.ist.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ist.operation.model.OperationType;
import ru.ist.user.dto.UserDto;

@Data
@AllArgsConstructor
public class CategoryDto {
    private final Long id;
    private final UserDto user;
    private final String title;
    private final OperationType operationType;
}

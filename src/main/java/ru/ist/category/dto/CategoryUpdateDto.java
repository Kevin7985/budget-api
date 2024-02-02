package ru.ist.category.dto;

import lombok.Data;
import ru.ist.category.exceptions.CategoryValidation;
import ru.ist.operation.model.OperationType;

@Data
public class CategoryUpdateDto {
    private String title;

    public CategoryUpdateDto(String title, OperationType operationType) {
        if (title != null && (title.length() < 3 || title.length() > 50)) {
            throw new CategoryValidation("Название категории должно быть длиннее 3 символов и короче 50");
        }

        this.title = title;
    }
}

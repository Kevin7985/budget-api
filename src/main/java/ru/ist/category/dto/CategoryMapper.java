package ru.ist.category.dto;

import org.springframework.stereotype.Component;
import ru.ist.category.model.Category;

@Component
public class CategoryMapper {
    public Category toCategory(CategoryInputDto categoryInputDto) {
        return new Category(
                null,
                categoryInputDto.getTitle(),
                categoryInputDto.getOperationType()
        );
    }

    public CategoryDto toCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getTitle(),
                category.getOperationType()
        );
    }
}

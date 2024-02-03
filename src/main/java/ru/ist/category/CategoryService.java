package ru.ist.category;

import ru.ist.category.dto.CategoryDto;
import ru.ist.category.dto.CategoryInputDto;
import ru.ist.category.dto.CategoryUpdateDto;
import ru.ist.operation.model.OperationType;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(Long userId, CategoryInputDto categoryInputDto);

    List<CategoryDto> getCategories(Long userId, OperationType operationType);

    CategoryDto getCategoryById(Long userId, Long id);

    CategoryDto updateCategoryById(Long userId, Long id, CategoryUpdateDto categoryUpdateDto);

    void deleteCategoryById(Long userId, Long id);
}

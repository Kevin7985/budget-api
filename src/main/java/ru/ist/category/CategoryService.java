package ru.ist.category;

import ru.ist.category.dto.CategoryDto;
import ru.ist.category.dto.CategoryInputDto;
import ru.ist.category.dto.CategoryUpdateDto;
import ru.ist.operation.model.OperationType;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryInputDto categoryInputDto);

    List<CategoryDto> getCategories(OperationType operationType);

    CategoryDto getCategoryById(Long id);

    CategoryDto updateCategoryById(Long id, CategoryUpdateDto categoryUpdateDto);

    void deleteCategoryById(Long id);
}

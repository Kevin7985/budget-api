package ru.ist.category;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ist.category.dto.CategoryDto;
import ru.ist.category.dto.CategoryInputDto;
import ru.ist.category.dto.CategoryUpdateDto;
import ru.ist.operation.model.OperationType;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
@Tag(name = "Categories", description = "Работа с категориями трат / доходов")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@RequestHeader("X-Ist-Budget-User-Id") Long userId, @RequestBody @Valid CategoryInputDto categoryInputDto) {
        return categoryService.createCategory(userId, categoryInputDto);
    }

    @GetMapping
    public List<CategoryDto> getCategories(
            @RequestHeader("X-Ist-Budget-User-Id") Long userId,
            @RequestParam(required = false) OperationType operationType
    ) {
        return categoryService.getCategories(userId, operationType);
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@RequestHeader("X-Ist-Budget-User-Id") Long userId, @PathVariable Long id) {
        return categoryService.getCategoryById(userId, id);
    }

    @PatchMapping("/{id}")
    public CategoryDto updateCategory(@RequestHeader("X-Ist-Budget-User-Id") Long userId, @PathVariable Long id, @RequestBody CategoryUpdateDto categoryUpdateDto) {
        return categoryService.updateCategoryById(userId, id, categoryUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@RequestHeader("X-Ist-Budget-User-Id") Long userId, @PathVariable Long id) {
        categoryService.deleteCategoryById(userId, id);
    }
}

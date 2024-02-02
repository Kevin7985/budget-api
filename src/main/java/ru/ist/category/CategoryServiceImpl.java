package ru.ist.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ist.category.dto.CategoryDto;
import ru.ist.category.dto.CategoryInputDto;
import ru.ist.category.dto.CategoryUpdateDto;
import ru.ist.category.model.Category;
import ru.ist.operation.model.OperationType;
import ru.ist.service.MapperService;
import ru.ist.service.ValidationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final MapperService mapperService;
    private final ValidationService validationService;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryInputDto categoryInputDto) {
        Category category = mapperService.toCategory(categoryInputDto);

        log.info("Создана новая категория: " + category);
        return mapperService.toCategoryDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getCategories(OperationType operationType) {
        if (operationType != null) {
            return categoryRepository.findByOperationType(operationType).stream()
                    .map(mapperService::toCategoryDto)
                    .collect(Collectors.toList());
        }

        return categoryRepository.findAll().stream()
                .map(mapperService::toCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = validationService.validateCategory(id);

        log.info("Получение информации о категории: " + category);
        return mapperService.toCategoryDto(category);
    }

    @Override
    public CategoryDto updateCategoryById(Long id, CategoryUpdateDto categoryUpdateDto) {
        Category category = validationService.validateCategory(id);

        category.setTitle(categoryUpdateDto.getTitle() == null ? category.getTitle() : categoryUpdateDto.getTitle());

        log.info("Обновлена информация о категории: " + category);
        return mapperService.toCategoryDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategoryById(Long id) {
        validationService.validateCategory(id);

        log.info("Категория с id = " + id + " успешно удалена");
        categoryRepository.deleteById(id);
    }
}

package ru.ist.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ist.category.dto.CategoryDto;
import ru.ist.category.dto.CategoryInputDto;
import ru.ist.category.dto.CategoryUpdateDto;
import ru.ist.category.model.Category;
import ru.ist.error.exceptions.Forbidden;
import ru.ist.operation.model.OperationType;
import ru.ist.service.MapperService;
import ru.ist.service.ValidationService;
import ru.ist.user.model.User;

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
    public CategoryDto createCategory(Long userId, CategoryInputDto categoryInputDto) {
        User user = validationService.validateUser(userId);
        Category category = categoryRepository.save(mapperService.toCategory(categoryInputDto, user));

        log.info("Создана новая категория: " + category);
        return mapperService.toCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getCategories(Long userId, OperationType operationType) {
        User user = validationService.validateUser(userId);

        if (operationType != null) {
            log.info("Получена информация о категориях по типу операции");
            return categoryRepository.findByUser_IdAndOperationType(userId, operationType).stream()
                    .map(mapperService::toCategoryDto)
                    .collect(Collectors.toList());
        }

        log.info("Получена информация обо всех категориях");
        return categoryRepository.findByUser_Id(userId).stream()
                .map(mapperService::toCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long userId, Long id) {
        User user = validationService.validateUser(userId);
        Category category = validationService.validateCategory(id);

        if (!category.getUser().getId().equals(userId)) {
            throw new Forbidden();
        }

        log.info("Получение информации о категории: " + category);
        return mapperService.toCategoryDto(category);
    }

    @Override
    public CategoryDto updateCategoryById(Long userId, Long id, CategoryUpdateDto categoryUpdateDto) {
        User user = validationService.validateUser(userId);
        Category category = validationService.validateCategory(id);

        if (!category.getUser().getId().equals(userId)) {
            throw new Forbidden();
        }

        category.setTitle(categoryUpdateDto.getTitle() == null ? category.getTitle() : categoryUpdateDto.getTitle());

        log.info("Обновлена информация о категории: " + category);
        return mapperService.toCategoryDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategoryById(Long userId, Long id) {
        User user = validationService.validateUser(userId);
        Category category = validationService.validateCategory(id);

        if (!category.getUser().getId().equals(userId)) {
            throw new Forbidden();
        }

        log.info("Категория с id = " + id + " успешно удалена");
        categoryRepository.deleteById(id);
    }
}

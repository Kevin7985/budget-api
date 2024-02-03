package ru.ist.category.dto;

import org.springframework.stereotype.Component;
import ru.ist.category.model.Category;
import ru.ist.user.dto.UserDto;
import ru.ist.user.model.User;

@Component
public class CategoryMapper {
    public Category toCategory(CategoryInputDto categoryInputDto, User user) {
        return new Category(
                null,
                user,
                categoryInputDto.getTitle(),
                categoryInputDto.getOperationType()
        );
    }

    public CategoryDto toCategoryDto(Category category, UserDto userDto) {
        return new CategoryDto(
                category.getId(),
                userDto,
                category.getTitle(),
                category.getOperationType()
        );
    }
}

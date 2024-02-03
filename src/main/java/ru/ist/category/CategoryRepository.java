package ru.ist.category;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ist.category.model.Category;
import ru.ist.operation.model.OperationType;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser_IdAndOperationType(Long userId, OperationType operationType);

    List<Category> findByUser_Id(Long userId);
}

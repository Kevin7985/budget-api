package ru.ist.operation;

import ru.ist.operation.dto.OperationDto;
import ru.ist.operation.dto.OperationInputDto;
import ru.ist.operation.dto.OperationUpdateDto;
import ru.ist.operation.model.OperationType;

import java.util.List;

public interface OperationService {
    OperationDto addOperation(Long userId, OperationInputDto operationInputDto);

    List<OperationDto> getOperations(Long userId, Long accountId, Long categoryId, OperationType operationType);

    OperationDto getOperationById(Long userId, Long id);

    OperationDto updateOperationById(Long userId, Long id, OperationUpdateDto operationUpdateDto);

    void deleteOperationById(Long userId, Long id);
}

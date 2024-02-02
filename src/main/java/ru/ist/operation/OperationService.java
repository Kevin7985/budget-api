package ru.ist.operation;

import ru.ist.operation.dto.OperationDto;
import ru.ist.operation.dto.OperationInputDto;
import ru.ist.operation.dto.OperationUpdateDto;
import ru.ist.operation.model.OperationType;

import java.util.List;

public interface OperationService {
    OperationDto addOperation(OperationInputDto operationInputDto);

    List<OperationDto> getOperations(Long accountId, Long categoryId, OperationType operationType);

    OperationDto getOperationById(Long id);

    OperationDto updateOperationById(Long id, OperationUpdateDto operationUpdateDto);

    void deleteOperationById(Long id);
}

package ru.ist.operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ist.operation.dto.OperationDto;
import ru.ist.operation.dto.OperationInputDto;
import ru.ist.operation.dto.OperationUpdateDto;
import ru.ist.operation.model.OperationType;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "Operations", description = "Работа с операциями")
public class OperationController {
    private final OperationService operationService;

    @PostMapping("/operations")
    @ResponseStatus(HttpStatus.CREATED)
    public OperationDto addOperation(@RequestBody @Valid OperationInputDto operationInputDto) {
        return operationService.addOperation(operationInputDto);
    }

    @GetMapping("/operations")
    public List<OperationDto> getOperations(
            @RequestParam(required = false) Long accountId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) OperationType operationType
            ) {
        return operationService.getOperations(accountId, categoryId, operationType);
    }

    @GetMapping("/operations/{id}")
    public OperationDto getOperationById(@PathVariable Long id) {
        return operationService.getOperationById(id);
    }

    @PatchMapping("/operations/{id}")
    public OperationDto updateOperation(@PathVariable Long id, @RequestBody OperationUpdateDto operationUpdateDto) {
        return operationService.updateOperationById(id, operationUpdateDto);
    }

    @DeleteMapping("/operations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOperation(@PathVariable Long id) {
        operationService.deleteOperationById(id);
    }
}

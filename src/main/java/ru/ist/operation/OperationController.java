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
    public OperationDto addOperation(@RequestHeader("X-Ist-Budget-User-Id") Long userId, @RequestBody @Valid OperationInputDto operationInputDto) {
        return operationService.addOperation(userId, operationInputDto);
    }

    @GetMapping("/operations")
    public List<OperationDto> getOperations(
            @RequestHeader("X-Ist-Budget-User-Id") Long userId,
            @RequestParam(required = false) Long accountId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) OperationType operationType
            ) {
        return operationService.getOperations(userId, accountId, categoryId, operationType);
    }

    @GetMapping("/operations/{id}")
    public OperationDto getOperationById(@RequestHeader("X-Ist-Budget-User-Id") Long userId, @PathVariable Long id) {
        return operationService.getOperationById(userId, id);
    }

    @PatchMapping("/operations/{id}")
    public OperationDto updateOperation(@RequestHeader("X-Ist-Budget-User-Id") Long userId, @PathVariable Long id, @RequestBody OperationUpdateDto operationUpdateDto) {
        return operationService.updateOperationById(userId, id, operationUpdateDto);
    }

    @DeleteMapping("/operations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOperation(@RequestHeader("X-Ist-Budget-User-Id") Long userId, @PathVariable Long id) {
        operationService.deleteOperationById(userId, id);
    }
}

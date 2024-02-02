package ru.ist.operation;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ist.account.model.Account;
import ru.ist.category.model.Category;
import ru.ist.operation.dto.OperationDto;
import ru.ist.operation.dto.OperationInputDto;
import ru.ist.operation.dto.OperationUpdateDto;
import ru.ist.operation.model.Operation;
import ru.ist.operation.model.OperationType;
import ru.ist.operation.model.QOperation;
import ru.ist.service.MapperService;
import ru.ist.service.ValidationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OperationServiceImpl implements OperationService {
    private final EntityManager entityManager;

    private final MapperService mapperService;
    private final ValidationService validationService;
    private final OperationRepository operationRepository;

    @Override
    public OperationDto addOperation(OperationInputDto operationInputDto) {
        Account account = validationService.validateAccount(operationInputDto.getAccount_id());
        Category category = validationService.validateCategory(operationInputDto.getCategory_id());

        Operation operation = mapperService.toOperation(operationInputDto, account, category);

        log.info("Добавлена новая операция: " + operation);
        return mapperService.toOperationDto(operationRepository.save(operation));
    }

    @Override
    public List<OperationDto> getOperations(Long accountId, Long categoryId, OperationType operationType) {
        QOperation qOperation = QOperation.operation;

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        JPAQuery<Operation> query = queryFactory.selectFrom(qOperation);

        if (accountId != null) {
            query.where(qOperation.account.id.eq(accountId));
        }

        if (categoryId != null) {
            query.where(qOperation.category.id.eq(categoryId));
        }

        if (operationType != null) {
            query.where(qOperation.operationType.eq(operationType));
        }

        return query.fetch().stream()
                .map(mapperService::toOperationDto)
                .collect(Collectors.toList());
    }

    @Override
    public OperationDto getOperationById(Long id) {
        Operation operation = validationService.validateOperation(id);

        log.info("Получена информация об операции: " + operation);
        return mapperService.toOperationDto(operation);
    }

    @Override
    public OperationDto updateOperationById(Long id, OperationUpdateDto operationUpdateDto) {
        Operation operation = validationService.validateOperation(id);

        if (operationUpdateDto.getAccountId() != null) {
            Account account = validationService.validateAccount(operationUpdateDto.getAccountId());

            operation.setAccount(account);
        }

        if (operationUpdateDto.getCategoryId() != null) {
            Category category = validationService.validateCategory(operationUpdateDto.getCategoryId());

            operation.setCategory(category);
        }

        operation.setOperationType(operationUpdateDto.getOperationType() == null ? operation.getOperationType() : operationUpdateDto.getOperationType());
        operation.setAmount(operationUpdateDto.getAmount() == null ? operation.getAmount() : operationUpdateDto.getAmount());

        return mapperService.toOperationDto(operationRepository.save(operation));
    }

    @Override
    public void deleteOperationById(Long id) {
        Operation operation = validationService.validateOperation(id);

        log.info("Операция с id = " + id + " успешно удалена");
        operationRepository.deleteById(id);
    }
}

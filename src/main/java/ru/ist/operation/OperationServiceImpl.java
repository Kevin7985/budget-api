package ru.ist.operation;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ist.account.AccountRepository;
import ru.ist.account.model.Account;
import ru.ist.category.model.Category;
import ru.ist.operation.dto.OperationDto;
import ru.ist.operation.dto.OperationInputDto;
import ru.ist.operation.dto.OperationUpdateDto;
import ru.ist.operation.exceptions.OperationValidation;
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
    private final AccountRepository accountRepository;

    @Override
    public OperationDto addOperation(OperationInputDto operationInputDto) {
        Account account = validationService.validateAccount(operationInputDto.getAccount_id());
        Category category = validationService.validateCategory(operationInputDto.getCategory_id());

        if (operationInputDto.getOperationType().equals(OperationType.OUTCOME)) {
            account.setAmount(account.getAmount() - operationInputDto.getAmount());
        } else if (operationInputDto.getOperationType().equals(OperationType.INCOME)) {
            account.setAmount(account.getAmount() + operationInputDto.getAmount());
        }

        Operation operation = operationRepository.save(mapperService.toOperation(operationInputDto, account, category));

        accountRepository.save(account);

        log.info("Добавлена новая операция: " + operation);
        return mapperService.toOperationDto(operation);
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
        Account account = validationService.validateAccount(operation.getAccount().getId());

        if (operationUpdateDto.getAccountId() != null) {
            Account acc = validationService.validateAccount(operationUpdateDto.getAccountId());

            operation.setAccount(acc);
        }

        if (operationUpdateDto.getCategoryId() != null) {
            Category category = validationService.validateCategory(operationUpdateDto.getCategoryId());

            operation.setCategory(category);
        }

        if (operation.getOperationType().equals(OperationType.INCOME)) {
            account.setAmount(account.getAmount() - operation.getAmount());
        } else if (operation.getOperationType().equals(OperationType.OUTCOME)) {
            account.setAmount(account.getAmount() + operation.getAmount());
        }

        operation.setOperationType(operationUpdateDto.getOperationType() == null ? operation.getOperationType() : operationUpdateDto.getOperationType());
        operation.setAmount(operationUpdateDto.getAmount() == null ? operation.getAmount() : operationUpdateDto.getAmount());

        if (operation.getOperationType().equals(OperationType.INCOME)) {
            account.setAmount(account.getAmount() + operation.getAmount());
        } else if (operation.getOperationType().equals(OperationType.OUTCOME)) {
            account.setAmount(account.getAmount() - operation.getAmount());
        }

        operation = operationRepository.save(operation);
        accountRepository.save(account);

        return mapperService.toOperationDto(operation);
    }

    @Override
    public void deleteOperationById(Long id) {
        Operation operation = validationService.validateOperation(id);
        Account account = validationService.validateAccount(operation.getAccount().getId());

        if (operation.getOperationType().equals(OperationType.INCOME)) {
            account.setAmount(account.getAmount() - operation.getAmount());
        } else if (operation.getOperationType().equals(OperationType.OUTCOME)) {
            account.setAmount(account.getAmount() + operation.getAmount());
        }

        log.info("Операция с id = " + id + " успешно удалена");
        operationRepository.deleteById(id);
    }
}

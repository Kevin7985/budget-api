package ru.ist.operation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.ist.operation.model.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long>, QuerydslPredicateExecutor<Operation> { }

package fr.sg.cib.gbto.dao.mapper;

import fr.sg.cib.gbto.dao.dtos.Statement;
import fr.sg.cib.gbto.domain.StatementEntity;
import org.springframework.stereotype.Service;

@Service
public class StatementMapper {

    public StatementEntity toStatementEntity(Statement statement) {
        StatementEntity statementEntity = new StatementEntity();
        statementEntity.setAmount(statement.getAmount());
        statementEntity.setBalance(statement.getBalance());
        statementEntity.setOperationDate(statement.getOperationDate());
        statementEntity.setOperationType(statement.getOperationType());
        return statementEntity;
    }

    public Statement toStatementDto(StatementEntity statementEntity) {
        return Statement.builder()
                .id(statementEntity.getId())
                .amount(statementEntity.getAmount())
                .balance(statementEntity.getBalance())
                .operationDate(statementEntity.getOperationDate())
                .operationType(statementEntity.getOperationType())
                .build();
    }
}

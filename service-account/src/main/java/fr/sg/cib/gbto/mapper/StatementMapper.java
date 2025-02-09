package fr.sg.cib.gbto.mapper;

import fr.sg.cib.gbto.dao.dtos.Statement;
import fr.sg.cib.gbto.domain.AccountStatement;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatementMapper {

    public AccountStatement toAccountStatement(Statement statement) {
        return Optional.ofNullable(AccountStatement.builder()
                        .id(statement.getId())
                        .amount(statement.getAmount())
                        .operationDate(statement.getOperationDate())
                        .type(statement.getOperationType())
                        .balance(statement.getBalance())
                        .build())
                .orElse(null);
    }
}

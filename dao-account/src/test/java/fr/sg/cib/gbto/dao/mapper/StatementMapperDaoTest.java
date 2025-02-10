package fr.sg.cib.gbto.dao.mapper;

import fr.sg.cib.gbto.dao.dtos.Statement;
import fr.sg.cib.gbto.domain.StatementEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class StatementMapperDaoTest {
    private StatementMapperDao statementMapperDao;

    @BeforeEach
    void setUp() {
        // Initialization mapper
        statementMapperDao = new StatementMapperDao();
    }

    @Test
    void testToStatementEntity() {
        // Given
        Statement statement = buildStatement();
        // When
        StatementEntity result = statementMapperDao.toStatementEntity(statement);

        // Then
        assertNotNull(result);

        assertEquals(statement.getAmount(), result.getAmount());
        assertEquals(statement.getBalance(), result.getBalance());
        assertEquals(statement.getOperationDate(), result.getOperationDate());
        assertEquals(statement.getOperationType(), result.getOperationType());
    }

    @Test
    void testToStatementDto() {
        // Given
        StatementEntity statementEntity = buildStatementEntity();
        // When
        Statement result = statementMapperDao.toStatementDto(statementEntity);

        // Then
        assertNotNull(result);

        assertEquals(statementEntity.getAmount(), result.getAmount());
        assertEquals(statementEntity.getBalance(), result.getBalance());
        assertEquals(statementEntity.getOperationDate(), result.getOperationDate());
        assertEquals(statementEntity.getOperationType(), result.getOperationType());
    }

    private Statement buildStatement() {
        return Statement.builder()
                .amount(100.0)
                .balance(1000.0)
                .operationDate(LocalDateTime.of(2025, 2, 9, 23, 30))
                .operationType("DEPOSIT")
                .build();
    }

    private StatementEntity buildStatementEntity() {
        StatementEntity statementEntity = new StatementEntity();
        statementEntity.setAmount(100.0);
        statementEntity.setBalance(1000.0);
        statementEntity.setOperationDate(LocalDateTime.of(2025, 2, 9, 23, 30));
        statementEntity.setOperationType("DEPOSIT");
        return statementEntity;
    }

}
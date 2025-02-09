package fr.sg.cib.gbto.dao.adapter;

import fr.sg.cib.gbto.dao.dtos.Statement;
import fr.sg.cib.gbto.dao.mapper.StatementMapper;
import fr.sg.cib.gbto.dao.repository.StatementRepository;
import fr.sg.cib.gbto.domain.StatementEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class StatementDaoAdapterTest {
    @Mock
    private StatementMapper statementMapper;

    @Mock
    private StatementRepository statementRepository;

    @InjectMocks
    private StatementDaoAdapter statementDaoAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_save_statementEntity() {
        Statement statement = buildStatement();
        StatementEntity statementEntity = buildStatementEntity();
        // When
        when(statementMapper.toStatementEntity(statement)).thenReturn(statementEntity);

        statementDaoAdapter.save(statement);

        // Then
        verify(statementRepository, times(1)).save(statementEntity);
    }

    private Statement buildStatement() {
        return Statement.builder()
                .id(1L)
                .amount(100.0)
                .operationType("DEPOSIT")
                .build();
    }

    private StatementEntity buildStatementEntity() {
        StatementEntity statementEntity = new StatementEntity();
        statementEntity.setId(1L);
        statementEntity.setAmount(100.0);
        statementEntity.setOperationType("DEPOSIT");
        return statementEntity;
    }

}
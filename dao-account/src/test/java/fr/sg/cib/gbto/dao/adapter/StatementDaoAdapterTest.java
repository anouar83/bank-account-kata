package fr.sg.cib.gbto.dao.adapter;

import fr.sg.cib.gbto.dao.dtos.Account;
import fr.sg.cib.gbto.dao.dtos.Statement;
import fr.sg.cib.gbto.dao.mapper.AccountMapperDao;
import fr.sg.cib.gbto.dao.mapper.StatementMapperDao;
import fr.sg.cib.gbto.dao.repository.StatementRepository;
import fr.sg.cib.gbto.domain.AccountEntity;
import fr.sg.cib.gbto.domain.StatementEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class StatementDaoAdapterTest {
    @Mock
    private StatementMapperDao statementMapperDao;
    @Mock
    private AccountMapperDao accountMapperDao;

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
        Account account = buildAccountDto();
        AccountEntity accountEntity = buildAccountEntity();
        // When
        when(statementMapperDao.toStatementEntity(statement)).thenReturn(statementEntity);
        when(accountMapperDao.toAccountEntity(account)).thenReturn(accountEntity);

        statementDaoAdapter.save(statement, account);

        // Then
        verify(statementRepository, times(1)).save(statementEntity);
    }

    @Test
    void test_FindByAccountId() {
        //Given
        Statement statement = buildStatement();
        StatementEntity statementEntity1 = new StatementEntity();
        StatementEntity statementEntity2 = new StatementEntity();
        List<StatementEntity> statementEntities = List.of(statementEntity1, statementEntity2);

        // When
        when(statementRepository.findByAccountId(1L)).thenReturn(statementEntities);

        when(statementMapperDao.toStatementDto(statementEntity1)).thenReturn(statement);
        when(statementMapperDao.toStatementDto(statementEntity2)).thenReturn(statement);

        List<Statement> result = statementDaoAdapter.findByAccountId(1L);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertSame(statement, result.get(0));
        assertSame(statement, result.get(1));

        verify(statementRepository).findByAccountId(1L);
    }

    private Statement buildStatement() {
        return Statement.builder()
                .id(1L)
                .amount(100.0)
                .operationType("DEPOSIT")
                .build();
    }

    private AccountEntity buildAccountEntity() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1L);
        accountEntity.setBalance(1000.0);
        return accountEntity;
    }

    private Account buildAccountDto() {
        return Account.builder()
                .id(1L)
                .balance(1000.0)
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
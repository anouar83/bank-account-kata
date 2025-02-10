package fr.sg.cib.gbto.service;

import fr.sg.cib.gbto.dao.adapter.AccountDao;
import fr.sg.cib.gbto.dao.adapter.StatementDao;
import fr.sg.cib.gbto.dao.dtos.Account;
import fr.sg.cib.gbto.dao.dtos.Statement;
import fr.sg.cib.gbto.dto.AccountStatement;
import fr.sg.cib.gbto.enums.OperationType;
import fr.sg.cib.gbto.exceptions.BalanceNotSufficientException;
import fr.sg.cib.gbto.exceptions.BankAccountNotFoundException;
import fr.sg.cib.gbto.mapper.StatementMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountServiceImplTest {

    @InjectMocks
    private BankAccountServiceImpl bankAccountService;
    @Mock
    private AccountDao accountDao;
    @Mock
    private StatementDao statementDao;
    @Mock
    private StatementMapper statementMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void when_withdrawal_nominal_case() throws BankAccountNotFoundException, BalanceNotSufficientException {
        Account account = Account.builder()
                .id(1L)
                .balance(100.0)
                .build();
        Mockito.when(accountDao.findById(1L)).thenReturn(Optional.of(account));
        Mockito.when(accountDao.save(account)).thenReturn(account);
        Account updatedAccount = bankAccountService.withdrawal(1L, 50.0);
        assertNotNull(updatedAccount);
        assertEquals(50.0, updatedAccount.getBalance());
    }

    @Test
    void should_Throw_Exception_BalanceNotSufficientException_when_withdrawal_and_InsufficientFunds() {
        Account account = Account.builder()
                .id(1L)
                .balance(100.0)
                .build();
        Mockito.when(accountDao.findById(1L)).thenReturn(Optional.of(account));
        assertThrows(BalanceNotSufficientException.class, () -> bankAccountService.withdrawal(1L, 150.0));
    }

    @Test
    void should_Throw_Exception_BankAccountNotFoundException_when_withdrawal_and_AccountNotFound() {
        Account account = Account.builder()
                .id(1L)
                .balance(100.0)
                .build();
        Mockito.when(accountDao.findById(1L)).thenReturn(Optional.of(account));
        assertThrows(BankAccountNotFoundException.class, () -> bankAccountService.withdrawal(2L, 150.0));
    }

    @Test
    void when_deposit_nominal_case() throws BankAccountNotFoundException {
        Account account = Account.builder()
                .id(1L)
                .balance(100.0)
                .build();
        Mockito.when(accountDao.findById(1L)).thenReturn(Optional.of(account));
        Mockito.when(accountDao.save(account)).thenReturn(account);
        Account updatedAccount = bankAccountService.deposit(1L, 50.0);
        assertNotNull(updatedAccount);
        assertEquals(150.0, updatedAccount.getBalance());
    }

    @Test
    void when_accountStatement_nominal_case() {
        Account account = Account.builder()
                .id(1L)
                .balance(100.0)
                .build();

        Statement statement = Statement.builder()
                .id(1L)
                .operationType(OperationType.DEPOSIT.name())
                .amount(10.0)
                .balance(110.0)
                .operationDate(LocalDateTime.now())
                .build();

        AccountStatement accountStatement = buildAccountStatement();

        Mockito.when(statementDao.findByAccountId(1L)).thenReturn(List.of(statement));
        Mockito.when(statementMapper.toAccountStatement(statement)).thenReturn(accountStatement);

        List<AccountStatement> accountStatementList = bankAccountService.accountStatement(1L);
        assertNotNull(accountStatementList);
        assertEquals(accountStatementList.get(0).getId(), statement.getId());
        assertEquals(accountStatementList.get(0).getAmount(), statement.getAmount());
        assertEquals(accountStatementList.get(0).getType(), statement.getOperationType());

    }

    private AccountStatement buildAccountStatement() {
        return AccountStatement
                .builder()
                .id(1L)
                .type(OperationType.DEPOSIT.name())
                .amount(10.0)
                .operationDate(LocalDateTime.now())
                .build();
    }
}
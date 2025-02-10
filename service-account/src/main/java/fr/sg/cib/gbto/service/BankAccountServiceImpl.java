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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private AccountDao accountDao;
    private StatementDao statementDao;
    private StatementMapper statementMapper;

    @Override
    public Account withdrawal(Long accountId, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
        Account bankAccount = accountDao.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));
        if (bankAccount.getBalance() < amount)
            throw new BalanceNotSufficientException("Balance not sufficient");
        Statement accountOperation = buildStatement(amount, OperationType.WITHDRAWAL.name());
        statementDao.save(accountOperation, bankAccount);
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        accountDao.save(bankAccount);
        return bankAccount;
    }

    public Account deposit(Long accountId, double amount) throws BankAccountNotFoundException {
        Account bankAccount = accountDao.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));
        Statement accountOperation = buildStatement(amount, OperationType.DEPOSIT.name());
        statementDao.save(accountOperation, bankAccount);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        accountDao.save(bankAccount);
        return bankAccount;
    }

    @Override
    public List<AccountStatement> accountStatement(Long accountId) {
        List<Statement> accountOperations = statementDao.findByAccountId(accountId);
        return accountOperations.stream()
                .map(statementMapper::toAccountStatement)
                .collect(Collectors.toList());
    }

    private Statement buildStatement(double amount, String operationType) {
        return Statement.builder()
                .amount(amount)
                .operationType(operationType)
                .operationDate(LocalDateTime.now())
                .build();
    }

}

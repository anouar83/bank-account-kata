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
    @Transactional
    public Account withdrawal(Long accountId, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
        Account bankAccount = accountDao.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));
        if (bankAccount.getBalance() < amount)
            throw new BalanceNotSufficientException("Balance not sufficient");
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        Statement accountOperation = buildStatement(amount, bankAccount.getBalance(), OperationType.WITHDRAWAL.name());
        statementDao.save(accountOperation, bankAccount);
        accountDao.save(bankAccount);
        return bankAccount;
    }

    @Override
    @Transactional
    public Account deposit(Long accountId, double amount) throws BankAccountNotFoundException {
        Account bankAccount = accountDao.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        Statement accountOperation = buildStatement(amount, bankAccount.getBalance(), OperationType.DEPOSIT.name());
        statementDao.save(accountOperation, bankAccount);
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

    private Statement buildStatement(double amount, double balance, String operationType) {
        return Statement.builder()
                .amount(amount)
                .balance(balance)
                .operationType(operationType)
                .operationDate(LocalDateTime.now())
                .build();
    }

}

package fr.sg.cib.gbto.service;

import fr.sg.cib.gbto.domain.AccountEntity;
import fr.sg.cib.gbto.domain.AccountStatement;
import fr.sg.cib.gbto.exceptions.BalanceNotSufficientException;
import fr.sg.cib.gbto.exceptions.BankAccountNotFoundException;

import java.util.List;

public interface BankAccountService {
    AccountEntity deposit(Long accountId, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;
    AccountEntity withdrawal(Long accountId, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<AccountStatement> accountStatement(Long accountId);

}

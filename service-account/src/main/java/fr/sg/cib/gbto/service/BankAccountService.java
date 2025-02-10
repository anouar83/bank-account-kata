package fr.sg.cib.gbto.service;

import fr.sg.cib.gbto.dao.dtos.Account;
import fr.sg.cib.gbto.dto.AccountStatement;
import fr.sg.cib.gbto.exceptions.BalanceNotSufficientException;
import fr.sg.cib.gbto.exceptions.BankAccountNotFoundException;

import java.util.List;

public interface BankAccountService {
    Account deposit(Long accountId, double amount) throws BankAccountNotFoundException;
    Account withdrawal(Long accountId, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<AccountStatement> accountStatement(Long accountId);

}

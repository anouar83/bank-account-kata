package fr.sg.cib.gbto.dao.adapter;

import fr.sg.cib.gbto.dao.dtos.Account;
import fr.sg.cib.gbto.domain.AccountEntity;

import java.util.Optional;

public interface AccountDao {

    Optional<Account> findById(long accountId);
    Account save(Account bankAccount);
}

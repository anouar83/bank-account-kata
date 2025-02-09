package fr.sg.cib.gbto.dao.adapter;

import fr.sg.cib.gbto.dao.dtos.Account;

import java.util.Optional;

public interface AccountDao {

    Optional<Account> findById(long accountId);
    void save(Account bankAccount);
}

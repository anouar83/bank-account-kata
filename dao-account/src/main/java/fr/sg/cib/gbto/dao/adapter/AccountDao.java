package fr.sg.cib.gbto.dao.adapter;

import fr.sg.cib.gbto.dao.dtos.Account;

public interface AccountDao {

    Account findById(long accountId);
    void save(Account bankAccount);
}

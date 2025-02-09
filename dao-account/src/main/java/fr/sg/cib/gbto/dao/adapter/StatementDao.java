package fr.sg.cib.gbto.dao.adapter;

import fr.sg.cib.gbto.dao.dtos.Account;
import fr.sg.cib.gbto.dao.dtos.Statement;

import java.util.List;

public interface StatementDao {

    void save(Statement statement, Account bankAccount);

    List<Statement> findByAccountId(Long accountId);
}

package fr.sg.cib.gbto.service;

import fr.sg.cib.gbto.domain.AccountStatement;

import java.util.List;

public interface StatementPrinter {

    void print(List<AccountStatement> statements);
}

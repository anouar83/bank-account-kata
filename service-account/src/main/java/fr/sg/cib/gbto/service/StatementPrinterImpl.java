package fr.sg.cib.gbto.service;

import fr.sg.cib.gbto.domain.AccountStatement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatementPrinterImpl implements StatementPrinter {
    @Override
    public void print(List<AccountStatement> statements) {
        System.out.println("Date                | Amount  | Balance");
        statements.stream().map(statement -> statement.getOperationDate() + " | " +
                statement.getAmount() + " | " +
                statement.getBalance()).forEach(System.out::println);
    }
}

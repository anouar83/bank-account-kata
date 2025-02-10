package fr.sg.cib.gbto.service;

import fr.sg.cib.gbto.dto.AccountStatement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatementPrinterImpl implements StatementPrinter {
    @Override
    public void print(List<AccountStatement> statements) {
        System.out.println("Date                | Amount  | Operation type  | Balance");
        statements.stream().map(statement -> statement.getOperationDate() + " | " +
                statement.getAmount() + " | " +
                statement.getType() + " | " +
                statement.getBalance()).forEach(System.out::println);
    }
}

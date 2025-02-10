package fr.sg.cib.gbto.service;

import fr.sg.cib.gbto.dto.AccountStatement;
import fr.sg.cib.gbto.enums.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StatementPrinterImplTest {

    @InjectMocks
    private StatementPrinterImpl statementPrinter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPrint_outputCapture() {
        AccountStatement statement1 = buildStatement(1L, 10.00, OperationType.WITHDRAWAL.name(), 100.00);
        AccountStatement statement2 = buildStatement(2L, 20.00, OperationType.DEPOSIT.name(),200.00);
        List<AccountStatement> statements = Arrays.asList(statement1, statement2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        statementPrinter.print(statements);

        String output = outputStream.toString();
        assertTrue(output.contains("Date                | Amount  | Operation type  | Balance"));
        assertTrue(output.contains("2025-02-07T10:30 | 10.0 | WITHDRAWAL | 100.0"));
        assertTrue(output.contains("2025-02-07T10:30 | 20.0 | DEPOSIT | 200.0"));

        System.setOut(System.out);
    }

    private AccountStatement buildStatement(Long id, double amount, String operationType, double balance){
        AccountStatement statement = AccountStatement
                .builder()
                .id(id)
                .amount(amount)
                .type(operationType)
                .balance(balance)
                .operationDate(LocalDateTime.of(2025, 2, 7, 10, 30))
                .build();
        return statement;
    }
}
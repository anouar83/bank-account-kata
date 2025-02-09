package fr.sg.cib.gbto.service;

import fr.sg.cib.gbto.domain.AccountStatement;
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
        // Créer une liste de relevés de compte
        AccountStatement statement1 = buildStatement(1L, 10.00, 100.00);
        AccountStatement statement2 = buildStatement(2L, 20.00, 200.00);
        List<AccountStatement> statements = Arrays.asList(statement1, statement2);

        // Capturer la sortie de la méthode print
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);  // Rediriger la sortie standard vers le PrintStream

        // Appeler la méthode print()
        statementPrinter.print(statements);

        // Vérification que le texte attendu est bien dans la sortie capturée
        String output = outputStream.toString();
        assertTrue(output.contains("Date                | Amount  | Balance"));
        assertTrue(output.contains("2025-02-07T10:30 | 10.0 | 100.0"));
        assertTrue(output.contains("2025-02-07T10:30 | 20.0 | 200.0"));

        // Réinitialiser la sortie standard
        System.setOut(System.out);
    }

    private AccountStatement buildStatement(Long id, double amount, double balance){
        AccountStatement statement = AccountStatement
                .builder()
                .id(id)
                .amount(amount)
                .balance(balance)
                .operationDate(LocalDateTime.of(2025, 2, 7, 10, 30))
                .build();
        return statement;
    }
}
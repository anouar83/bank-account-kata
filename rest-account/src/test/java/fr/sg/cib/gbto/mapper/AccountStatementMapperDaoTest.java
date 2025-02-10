package fr.sg.cib.gbto.mapper;

import fr.sg.cib.gbto.dto.AccountStatement;
import fr.sg.cib.gbto.dtos.AccountStatementDTO;
import fr.sg.cib.gbto.enums.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountStatementMapperDaoTest {

    private AccountStatementMapper accountStatementMapper;

    @BeforeEach
    void setUp() {
        accountStatementMapper = new AccountStatementMapper();
    }

    @Test
    void toAccountStatementDto() {
        AccountStatement accountStatement = AccountStatement.builder()
                .id(1L)
                .amount(100)
                .type(OperationType.DEPOSIT.name())
                .operationDate(LocalDateTime.now())
                .build();

        AccountStatementDTO accountStatementDTO = accountStatementMapper.toAccountStatementDto(accountStatement);
        assertEquals(accountStatementDTO.getId(), accountStatement.getId());
        assertEquals(accountStatementDTO.getAmount(), accountStatement.getAmount());
        assertEquals(accountStatementDTO.getType(), accountStatement.getType());
        assertEquals(accountStatementDTO.getOperationDate(), accountStatement.getOperationDate());
    }
}
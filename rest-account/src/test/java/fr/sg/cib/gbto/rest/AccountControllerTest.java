package fr.sg.cib.gbto.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.sg.cib.gbto.domain.AccountStatement;
import fr.sg.cib.gbto.dtos.AccountStatementDTO;
import fr.sg.cib.gbto.dtos.DepositDTO;
import fr.sg.cib.gbto.dtos.WithdrawDTO;
import fr.sg.cib.gbto.mapper.AccountStatementMapper;
import fr.sg.cib.gbto.service.BankAccountService;
import fr.sg.cib.gbto.service.StatementPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private BankAccountService bankService;
    @MockitoBean
    private AccountStatementMapper accountStatementMapper;
    @MockitoBean
    private StatementPrinter statementPrinter;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeposit() throws Exception {
        DepositDTO depositDTO = new DepositDTO();
        depositDTO.setAccountId(1L);
        depositDTO.setAmount(100.0);
        when(bankService.deposit(anyLong(), anyDouble())).thenReturn(null);
        mockMvc.perform(post("/account/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(depositDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(1L))
                .andExpect(jsonPath("$.amount").value(100.0));
    }

    @Test
    void withdraw() throws Exception {
        WithdrawDTO withdrawDTO = new WithdrawDTO();
        withdrawDTO.setAccountId(1L);
        withdrawDTO.setAmount(50.0);
        when(bankService.withdrawal(anyLong(), anyDouble())).thenReturn(null);
        mockMvc.perform(post("/account/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(withdrawDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(1L))
                .andExpect(jsonPath("$.amount").value(50.0));
    }

    @Test
    void getAccountStatement() throws Exception {
        LocalDateTime date = LocalDateTime.now();
        AccountStatementDTO accountStatementDTO = new AccountStatementDTO();
        accountStatementDTO.setId(1L);
        accountStatementDTO.setOperationDate(date);
        accountStatementDTO.setAmount(100.0);

        AccountStatement accountStatement = AccountStatement.builder()
                .id(1L)
                .operationDate(date)
                .amount(100.0)
                .build();
        when(bankService.accountStatement(anyLong())).thenReturn(Arrays.asList(accountStatement));
        when(accountStatementMapper.toAccountStatementDto(any())).thenReturn(accountStatementDTO);
        mockMvc.perform(get("/account/1/operations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].amount").value(100.0));
    }
}
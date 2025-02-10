package fr.sg.cib.gbto.rest;

import fr.sg.cib.gbto.dto.AccountStatement;
import fr.sg.cib.gbto.dtos.AccountStatementDTO;
import fr.sg.cib.gbto.dtos.DepositDTO;
import fr.sg.cib.gbto.dtos.WithdrawDTO;
import fr.sg.cib.gbto.exceptions.BalanceNotSufficientException;
import fr.sg.cib.gbto.exceptions.BankAccountNotFoundException;
import fr.sg.cib.gbto.mapper.AccountStatementMapper;
import fr.sg.cib.gbto.service.BankAccountService;
import fr.sg.cib.gbto.service.StatementPrinter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private final BankAccountService bankService;
    private final AccountStatementMapper accountStatementMapper;
    private final StatementPrinter statementPrinter;

    @PostMapping("/deposit")
    public ResponseEntity<DepositDTO> deposit(@RequestBody DepositDTO depositDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankService.deposit(depositDTO.getAccountId(), depositDTO.getAmount());
        return new ResponseEntity<>(depositDTO, HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<WithdrawDTO> withdraw(@RequestBody WithdrawDTO withdrawDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankService.withdrawal(withdrawDTO.getAccountId(), withdrawDTO.getAmount());
        return new ResponseEntity<>(withdrawDTO, HttpStatus.OK);
    }

    @GetMapping("{accountId}/operations")
    public ResponseEntity<List<AccountStatementDTO>> getAccountStatement(@PathVariable Long accountId){
        List<AccountStatementDTO> accountStatementDTOS = bankService.accountStatement(accountId)
                .stream()
                .map(accountStatementMapper::toAccountStatementDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(accountStatementDTOS, HttpStatus.OK);
    }

    @GetMapping("{accountId}/statement")
    public void printStatement(@PathVariable Long accountId) {
        List<AccountStatement> accountStatementList = bankService.accountStatement(accountId);
        statementPrinter.print(accountStatementList);
    }
}

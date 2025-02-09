package fr.sg.cib.gbto.mapper;

import fr.sg.cib.gbto.domain.AccountStatement;
import fr.sg.cib.gbto.dtos.AccountStatementDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountStatementMapper {

    public AccountStatementDTO toAccountStatementDto(AccountStatement accountStatement){
        AccountStatementDTO accountStatementDTO=new AccountStatementDTO();
        BeanUtils.copyProperties(accountStatement,accountStatementDTO);
        return accountStatementDTO;
    }
}

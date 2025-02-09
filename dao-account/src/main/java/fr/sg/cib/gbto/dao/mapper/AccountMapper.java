package fr.sg.cib.gbto.dao.mapper;

import fr.sg.cib.gbto.dao.dtos.Account;
import fr.sg.cib.gbto.domain.AccountEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

    public Account toAccountDto(AccountEntity accountEntity) {
        return Account.builder()
                .id(accountEntity.getId())
                .balance(accountEntity.getBalance())
                .build();
    }

    public AccountEntity toAccountEntity(Account accountDto) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(accountDto.getId());
        accountEntity.setBalance(accountDto.getBalance());
        return accountEntity;
    }
}

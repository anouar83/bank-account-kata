package fr.sg.cib.gbto.dao.adapter;

import fr.sg.cib.gbto.dao.dtos.Account;
import fr.sg.cib.gbto.dao.mapper.AccountMapper;
import fr.sg.cib.gbto.dao.repository.AccountRepository;
import fr.sg.cib.gbto.domain.AccountEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountDaoAdapter implements AccountDao{

    private AccountRepository accountRepository;
    private AccountMapper accountMapper;
    @Override
    public Optional<Account> findById(long accountId) {
        return accountRepository.findById(accountId)
                .map(accountMapper::toAccountDto);
    }

    @Override
    public Account save(Account bankAccount) {
        AccountEntity accountEntity =  accountMapper.toAccountEntity(bankAccount);
        AccountEntity savedAccountEntity = accountRepository.save(accountEntity);
        return accountMapper.toAccountDto(savedAccountEntity);
    }
}

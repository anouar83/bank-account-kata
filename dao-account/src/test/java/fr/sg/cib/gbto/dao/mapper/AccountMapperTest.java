package fr.sg.cib.gbto.dao.mapper;

import fr.sg.cib.gbto.dao.dtos.Account;
import fr.sg.cib.gbto.domain.AccountEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountMapperTest {

    private AccountMapper accountMapper;

    @BeforeEach
    void setUp() {
        // Initialization mapper
        accountMapper = new AccountMapper();
    }

    @Test
    void test_ToAccountDto() {
        //Given
        AccountEntity accountEntity = buildAccountEntity();
        //When
        Account result = accountMapper.toAccountDto(accountEntity);
        //Then
        assertNotNull(result);
        assertEquals(accountEntity.getId(), result.getId());
        assertEquals(accountEntity.getBalance(), result.getBalance());
    }

    @Test
    void test_ToAccountEntity() {
        //Given
        Account accountDto = buildAccount();
        //When
        AccountEntity result = accountMapper.toAccountEntity(accountDto);
        //Then
        assertNotNull(result);
        assertEquals(accountDto.getId(), result.getId());
        assertEquals(accountDto.getBalance(), result.getBalance());
    }

    private AccountEntity buildAccountEntity() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1L);
        accountEntity.setBalance(1000.0);
        return accountEntity;
    }

    private Account buildAccount() {
        return Account.builder()
                .id(1L)
                .balance(1000.0)
                .build();
    }
}
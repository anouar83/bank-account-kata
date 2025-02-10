package fr.sg.cib.gbto.dao.mapper;

import fr.sg.cib.gbto.dao.dtos.Account;
import fr.sg.cib.gbto.domain.AccountEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountMapperDaoTest {

    private AccountMapperDao accountMapperDao;

    @BeforeEach
    void setUp() {
        // Initialization mapper
        accountMapperDao = new AccountMapperDao();
    }

    @Test
    void test_ToAccountDto() {
        //Given
        AccountEntity accountEntity = buildAccountEntity();
        //When
        Account result = accountMapperDao.toAccountDto(accountEntity);
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
        AccountEntity result = accountMapperDao.toAccountEntity(accountDto);
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
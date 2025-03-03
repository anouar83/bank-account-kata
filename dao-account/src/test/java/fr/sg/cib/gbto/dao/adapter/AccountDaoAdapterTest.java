package fr.sg.cib.gbto.dao.adapter;

import fr.sg.cib.gbto.dao.dtos.Account;
import fr.sg.cib.gbto.dao.mapper.AccountMapperDao;
import fr.sg.cib.gbto.dao.repository.AccountRepository;
import fr.sg.cib.gbto.domain.AccountEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountDaoAdapterTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapperDao accountMapperDao;

    @InjectMocks
    private AccountDaoAdapter accountDaoAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_findById_nominal_case() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1L);
        accountEntity.setBalance(1000.0);

        Account accountDto = buildAccountDto();

        when(accountRepository.findById(1L)).thenReturn(Optional.of(accountEntity));
        when(accountMapperDao.toAccountDto(accountEntity)).thenReturn(accountDto);

        Optional<Account> result = accountDaoAdapter.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.get().getId());
        assertEquals(1000.0, result.get().getBalance());

        verify(accountRepository, times(1)).findById(1L);
        verify(accountMapperDao, times(1)).toAccountDto(accountEntity);
    }

    @Test
    void test_findById_accountNotFound() {
        //Given
        when(accountRepository.findById(2L)).thenReturn(Optional.empty());

        //When
        Optional<Account> result = accountDaoAdapter.findById(2L);

        //Then
        assert(result).isEmpty();
        verify(accountRepository, times(1)).findById(2L);
    }

    @Test
    void test_save() {
        //Given
        Account accountDto = buildAccountDto();
        AccountEntity accountEntity = buildAccountEntity();

        //when
        when(accountMapperDao.toAccountEntity(accountDto)).thenReturn(accountEntity);

        accountDaoAdapter.save(accountDto);

        // Then
        verify(accountRepository, times(1)).save(accountEntity);
    }

    private Account buildAccountDto() {
        return Account.builder()
                .id(1L)
                .balance(1000.0)
                .build();
    }

    private AccountEntity buildAccountEntity() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1L);
        accountEntity.setBalance(1000.0);
        return accountEntity;
    }
}
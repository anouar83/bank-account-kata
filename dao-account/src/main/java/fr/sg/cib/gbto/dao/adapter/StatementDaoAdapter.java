package fr.sg.cib.gbto.dao.adapter;

import fr.sg.cib.gbto.dao.dtos.Account;
import fr.sg.cib.gbto.dao.dtos.Statement;
import fr.sg.cib.gbto.dao.mapper.AccountMapper;
import fr.sg.cib.gbto.dao.mapper.StatementMapper;
import fr.sg.cib.gbto.dao.repository.StatementRepository;
import fr.sg.cib.gbto.domain.AccountEntity;
import fr.sg.cib.gbto.domain.StatementEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatementDaoAdapter implements StatementDao {

    private StatementMapper statementMapper;
    private AccountMapper accountMapper;
    private StatementRepository statementRepository;

    @Override
    public void save(Statement statement, Account bankAccount) {
        StatementEntity statementEntity = statementMapper.toStatementEntity(statement);
        AccountEntity accountEntity = accountMapper.toAccountEntity(bankAccount);
        statementEntity.setAccountEntity(accountEntity);
        statementRepository.save(statementEntity);
    }

    @Override
    public List<Statement> findByAccountId(Long accountId) {
        return statementRepository.findByAccountId(accountId)
                .stream()
                .map(statementMapper::toStatementDto)
                .collect(Collectors.toList());
    }
}

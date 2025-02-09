package fr.sg.cib.gbto.dao.adapter;

import fr.sg.cib.gbto.dao.dtos.Statement;
import fr.sg.cib.gbto.dao.mapper.StatementMapper;
import fr.sg.cib.gbto.dao.repository.StatementRepository;
import fr.sg.cib.gbto.domain.StatementEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatementDaoAdapter implements StatementDao {

    private StatementMapper statementMapper;
    private StatementRepository statementRepository;

    @Override
    public void save(Statement statement) {
        StatementEntity statementEntity = statementMapper.toStatementEntity(statement);
        statementRepository.save(statementEntity);
    }
}

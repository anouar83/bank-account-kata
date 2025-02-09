package fr.sg.cib.gbto.dao.repository;

import fr.sg.cib.gbto.domain.StatementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatementRepository extends JpaRepository<StatementEntity, Long> {
    List<StatementEntity> findByAccountId(Long accountId);
}

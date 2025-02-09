package fr.sg.cib.gbto.dao.repository;

import fr.sg.cib.gbto.domain.StatementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatementRepository extends JpaRepository<StatementEntity, Long> {

}

package fr.sg.cib.gbto.dao.repository;

import fr.sg.cib.gbto.domain.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}

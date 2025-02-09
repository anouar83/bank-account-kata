package fr.sg.cib.gbto.repositories;

import fr.sg.cib.gbto.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

package fr.sg.cib.gbto.repositories;

import fr.sg.cib.gbto.entities.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatementRepository extends JpaRepository<Statement, Long> {

}

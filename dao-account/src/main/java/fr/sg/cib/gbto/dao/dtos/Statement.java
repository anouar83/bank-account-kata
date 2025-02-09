package fr.sg.cib.gbto.dao.dtos;

import fr.sg.cib.gbto.domain.AccountEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Statement {
    private Long id;
    private LocalDateTime operationDate;
    private double amount;
    private double balance;
    private String operationType;
}

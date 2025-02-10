package fr.sg.cib.gbto.dao.dtos;

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

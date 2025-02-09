package fr.sg.cib.gbto.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountStatementDTO {

    private Long id;
    private LocalDateTime operationDate;
    private double amount;
    private String type;
}

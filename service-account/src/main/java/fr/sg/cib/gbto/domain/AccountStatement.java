package fr.sg.cib.gbto.domain;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AccountStatement {

    private Long id;
    private LocalDateTime operationDate;
    private double amount;
    private double balance;
    private String type;
}

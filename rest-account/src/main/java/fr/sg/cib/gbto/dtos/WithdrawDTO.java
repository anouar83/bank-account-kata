package fr.sg.cib.gbto.dtos;

import lombok.Data;

@Data
public class WithdrawDTO {
    private Long accountId;
    private double amount;
}

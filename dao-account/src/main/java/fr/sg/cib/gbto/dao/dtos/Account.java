package fr.sg.cib.gbto.dao.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {
    private Long id;
    private double balance;

    private int version;
}

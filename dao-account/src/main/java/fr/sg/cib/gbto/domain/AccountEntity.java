package fr.sg.cib.gbto.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double balance;
    @Version
    private int version;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<StatementEntity> statementEntities = new ArrayList<>();
}

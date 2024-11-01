package be.genealogie.domein.entiteit;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;

@Data
@Table(name = "NATUURLIJK_PERSOON")
@Entity
public class NatuurlijkPersoon {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;
    private String voornaam;
    private LocalDate geborenOp;
    private String geborenTe;
    private LocalDate overledenOp;
    private String overledenTe;
}

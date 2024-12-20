package be.genealogie.domein.entiteit;

import be.genealogie.domein.Geslacht;
import be.genealogie.domein.utils.LocalDateUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Optional;

@Data
@Table(name = "NATUURLIJK_PERSOON")
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class NatuurlijkPersoon {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAAM")
    private String naam;

    @Column(name = "VOORNAAM")
    private String voornaam;

    @Column(name = "GEBOREN_OP")
    private LocalDate geborenOp;

    @Column(name = "GEBOREN_TE")
    private String geborenTe;

    @Column(name = "OVERLEDEN_OP")
    private LocalDate overledenOp;

    @Column(name = "OVERLEDEN_TE")
    private String overledenTe;

    @Column(name = "GESLACHT")
    @Enumerated(EnumType.STRING)
    private Geslacht geslacht;

    public String volledigeNaam() {
        return voornaam + " " + naam;
    }

    public Integer leeftijd() {
        return Optional.ofNullable(geborenOp)
                .map(geborenOp -> LocalDateUtils.berekenLeeftijd(geborenOp, Optional.ofNullable(overledenOp).orElse(LocalDate.now())))
                .orElse(null);
    }
}

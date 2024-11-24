package be.genealogie.domein.entiteit;

import be.genealogie.domein.Geslacht;
import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Table(name = "RELATIE")
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Relatie {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSOON_1_ID")
    private NatuurlijkPersoon persoon1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSOON_2_ID")
    private NatuurlijkPersoon persoon2;

    @Column(name = "GEHUWD_OP")
    private LocalDate gehuwedOp;

    @Column(name = "GESCHEIDEN_OP")
    private LocalDate gescheidenOp;

    @Column(name = "GEHUWD_TE")
    private String gehuwedTe;

    public boolean gescheiden() {
        return gescheidenOp != null;
    }

    public NatuurlijkPersoon partnerVan(NatuurlijkPersoon persoon) {
        return (persoon1.getId().equals(persoon.getId()))?persoon2:persoon1 ;
    }
}

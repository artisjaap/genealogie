package be.genealogie.domein.entiteit;

import be.genealogie.domein.Geslacht;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Table(name = "RELATIE")
@Entity
@Builder
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

    public NatuurlijkPersoon man() {
        return (persoon1.getGeslacht() == Geslacht.MAN)?persoon1:persoon2;
    }

    public NatuurlijkPersoon vrouw() {
        return (persoon1.getGeslacht() == Geslacht.VROUW)?persoon1:persoon2;
    }
}

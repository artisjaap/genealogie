package be.genealogie.domein.entiteit;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Table(name = "HUWELIJK")
@Entity
public class Huwelijk {
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
}

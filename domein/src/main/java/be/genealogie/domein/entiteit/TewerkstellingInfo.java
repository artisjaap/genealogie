package be.genealogie.domein.entiteit;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "ONDERZOEKSINFO")
@Entity
public class TewerkstellingInfo {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSOON_ID")
    private NatuurlijkPersoon persoon;

    @Column(name = "BEROEP")
    private String beroep;
}

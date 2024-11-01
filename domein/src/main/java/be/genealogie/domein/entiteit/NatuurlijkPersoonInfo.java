package be.genealogie.domein.entiteit;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "NATUURLIJK_PERSOON_INFO")
@Entity
public class NatuurlijkPersoonInfo {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSOON_ID")
    private NatuurlijkPersoon persoon;

    @Column(name = "KAN_LEZEN")
    private Boolean kanLezen;

    @Column(name = "KAN_SCHRIJVEN")
    private Boolean kanSchrijven;

    @Column(name = "KAN_REKENEN")
    private Boolean kanRekenen;
}

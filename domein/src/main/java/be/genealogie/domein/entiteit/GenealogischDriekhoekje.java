package be.genealogie.domein.entiteit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "GENEALOGISCH_DRIEKHOEKJE")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenealogischDriekhoekje {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KIND_ID")
    private NatuurlijkPersoon kind;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOEDER_ID")
    private NatuurlijkPersoon moeder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VADER_ID")
    private NatuurlijkPersoon vader;
}

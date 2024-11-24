package be.genealogie.domein.entiteit;

import be.genealogie.domein.Geslacht;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @JoinColumn(name = "OUDER_1_ID")
    private NatuurlijkPersoon ouder1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OUDER_2_ID")
    private NatuurlijkPersoon ouder2;

    private NatuurlijkPersoon zoekOpGeslacht(Geslacht geslacht) {
        return Stream.of(ouder1, ouder2)
                .filter(ouder -> ouder.getGeslacht() == geslacht)
                .findFirst()
                .orElse(null);
    }

    public NatuurlijkPersoon vader() {
        return zoekOpGeslacht(Geslacht.MAN);
    }

    public NatuurlijkPersoon moeder() {
        return zoekOpGeslacht(Geslacht.VROUW);
    }



    public boolean heeftDezelfdeOudersAls(GenealogischDriekhoekje driehoekje) {
        Set<Long> oudersThis = Stream.of(ouder1, ouder2).filter(Objects::nonNull).map(NatuurlijkPersoon::getId).collect(Collectors.toSet());
        Set<Long> oudersNieuw = Stream.of(driehoekje.ouder1, driehoekje.ouder2).filter(Objects::nonNull).map(NatuurlijkPersoon::getId).collect(Collectors.toSet());
        return oudersThis.equals(oudersNieuw);
    }
}

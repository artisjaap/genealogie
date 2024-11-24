package be.genealogie.domein.repository;

import be.genealogie.domein.entiteit.GenealogischDriekhoekje;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface GenealogischDriekhoekjeRepository {

    GenealogischDriekhoekje save(GenealogischDriekhoekje genealogischDriekhoekje);

    Optional<GenealogischDriekhoekje> findByKind(NatuurlijkPersoon natuurlijkPersoon);

    List<GenealogischDriekhoekje> findByOuder1OrOuder2(NatuurlijkPersoon ouder1, NatuurlijkPersoon ouder2);

    List<GenealogischDriekhoekje> findByOuder1AndOuder2(NatuurlijkPersoon ouder1, NatuurlijkPersoon ouder2);

    default List<GenealogischDriekhoekje> findKinderenVan(NatuurlijkPersoon persoon) {
        return findByOuder1OrOuder2(persoon, persoon);
    }

    default List<GenealogischDriekhoekje> zoekOpBeideOuders(NatuurlijkPersoon ouder1, NatuurlijkPersoon ouder2) {
        return Stream.of(findByOuder1AndOuder2(ouder1, ouder2), findByOuder1AndOuder2(ouder2, ouder1) )
                .flatMap(Collection::stream)
                .toList();
    }

    default List<GenealogischDriekhoekje> zoekOpEenVanDeOuders(NatuurlijkPersoon ouder1, NatuurlijkPersoon ouder2){
        return Stream.of(zoekOpOuder(ouder1), zoekOpOuder(ouder2))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet())
                .stream().toList();
    }

    default  List<GenealogischDriekhoekje>  zoekOpOuder(NatuurlijkPersoon persoonDb){
        return findByOuder1OrOuder2(persoonDb, persoonDb);
    }

}

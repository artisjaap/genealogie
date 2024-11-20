package be.genealogie.domein.repository;

import be.genealogie.domein.entiteit.GenealogischDriekhoekje;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;

import java.util.List;
import java.util.Optional;

public interface GenealogischDriekhoekjeRepository {

    GenealogischDriekhoekje save(GenealogischDriekhoekje genealogischDriekhoekje);

    Optional<GenealogischDriekhoekje> findByKind(NatuurlijkPersoon natuurlijkPersoon);

    List<GenealogischDriekhoekje> findByMoederOrVader(NatuurlijkPersoon moeder, NatuurlijkPersoon vader);

    List<GenealogischDriekhoekje> findByMoederAndVader(NatuurlijkPersoon moeder, NatuurlijkPersoon vader);

    default List<GenealogischDriekhoekje> findKinderenVan(NatuurlijkPersoon persoon) {
        return findByMoederOrVader(persoon, persoon);
    }

}

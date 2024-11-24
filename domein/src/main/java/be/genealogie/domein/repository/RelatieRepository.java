package be.genealogie.domein.repository;

import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.entiteit.Relatie;

import java.util.List;
import java.util.Optional;

public interface RelatieRepository {
    Relatie save(Relatie persoon);

    Relatie getById(Long id);

    List<Relatie> findByPersoon1OrPersoon2(NatuurlijkPersoon persoon1, NatuurlijkPersoon persoon2);

    Optional<Relatie> findByPersoon1AndPersoon2(NatuurlijkPersoon persoon1, NatuurlijkPersoon persoon2);

    default List<Relatie> zoekRelatiesMet(NatuurlijkPersoon persoon){
        return findByPersoon1OrPersoon2(persoon, persoon);
    }
}

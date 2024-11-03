package be.genealogie.domein.repository;

import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.entiteit.Relatie;

import java.util.List;

public interface RelatieRepository {
    Relatie save(Relatie persoon);

    Relatie getById(Long id);

    List<Relatie> findByPersoon1OrPersoon2(NatuurlijkPersoon persoon1, NatuurlijkPersoon persoon2);

}

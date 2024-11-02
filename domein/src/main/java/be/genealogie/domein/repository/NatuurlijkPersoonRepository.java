package be.genealogie.domein.repository;

import be.genealogie.domein.entiteit.NatuurlijkPersoon;

import java.util.List;

public interface NatuurlijkPersoonRepository {
    NatuurlijkPersoon save(NatuurlijkPersoon persoon);

    NatuurlijkPersoon getById(Long id);

    List<NatuurlijkPersoon> findAll();

    List<NatuurlijkPersoon> findByNaamLikeOrVoornaamLike(String naam);
}

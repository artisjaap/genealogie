package be.genealogie.repository;

import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataJpaNatuurlijkPersoonRepository extends JpaRepository<NatuurlijkPersoon, Long>, NatuurlijkPersoonRepository {

    List<NatuurlijkPersoon> findByNaamLikeOrVoornaamLike(String naam, String voornaam);

    default List<NatuurlijkPersoon> findByNaamLikeOrVoornaamLike(String naam){
        return findByNaamLikeOrVoornaamLike(naam, naam);
    }

}

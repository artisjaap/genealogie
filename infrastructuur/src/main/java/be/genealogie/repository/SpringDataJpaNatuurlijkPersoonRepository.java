package be.genealogie.repository;

import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaNatuurlijkPersoonRepository extends JpaRepository<NatuurlijkPersoon, Long>, NatuurlijkPersoonRepository {
}

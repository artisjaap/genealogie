package be.genealogie.repository;

import be.genealogie.domein.entiteit.Relatie;
import be.genealogie.domein.repository.RelatieRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaRelatieRepository extends JpaRepository<Relatie, Long>, RelatieRepository {
}

package be.genealogie.repository;

import be.genealogie.domein.entiteit.Huwelijk;
import be.genealogie.domein.repository.HuwelijkRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaHuwelijkRepository extends JpaRepository<Huwelijk, Long>, HuwelijkRepository {
}

package be.genealogie.repository;

import be.genealogie.domein.entiteit.GenealogischDriekhoekje;
import be.genealogie.domein.repository.GenealogischDriekhoekjeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaGenealogischDriekhoekjeRepository extends JpaRepository<GenealogischDriekhoekje, Long>, GenealogischDriekhoekjeRepository {
}

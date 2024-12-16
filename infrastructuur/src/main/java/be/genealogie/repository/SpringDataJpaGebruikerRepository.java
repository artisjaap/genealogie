package be.genealogie.repository;

import be.genealogie.domein.entiteit.Gebruiker;
import be.genealogie.domein.repository.GebruikerRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaGebruikerRepository extends JpaRepository<Gebruiker, Long>, GebruikerRepository {
}

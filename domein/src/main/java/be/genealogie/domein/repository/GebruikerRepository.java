package be.genealogie.domein.repository;

import be.genealogie.domein.entiteit.Gebruiker;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

public interface GebruikerRepository {

    @Cacheable("gebruikers")
    Optional<Gebruiker> findByEmail(String email);

    Gebruiker save(Gebruiker gebruiker);

    List<Gebruiker> findAll();

    long count();

    Gebruiker getById(Long gebruikerId);

}

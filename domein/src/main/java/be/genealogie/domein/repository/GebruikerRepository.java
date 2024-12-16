package be.genealogie.domein.repository;

import be.genealogie.domein.entiteit.Gebruiker;

import java.util.Optional;

public interface GebruikerRepository {
    Optional<Gebruiker> findByEmail(String email);

    Gebruiker save(Gebruiker gebruiker);
}

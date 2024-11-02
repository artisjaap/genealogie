package be.genealogie.domein.repository;

import be.genealogie.domein.entiteit.Huwelijk;

public interface HuwelijkRepository {
    Huwelijk save(Huwelijk persoon);

    Huwelijk getById(Long id);

}

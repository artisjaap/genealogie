package be.genealogie.applicatie;

import be.genealogie.domein.dto.*;

public interface NatuurlijkPersoonMaken {
    NatuurlijkPersoonDTO maak(NieuwNatuurlijkPersoonDTO natuurlijkPersoonDTO);

    HuwelijkDto maakHuwelijk(HuwelijkDto huwelijk);


    HuwelijkDto maakHuwelijk(HuwelijkMetNieuwNatuurlijkPersoonDto huwelijk);
}

package be.genealogie.applicatie;

import be.genealogie.domein.dto.*;

public interface NatuurlijkPersoonMaken {
    NatuurlijkPersoonDTO maak(NieuwNatuurlijkPersoonDTO natuurlijkPersoonDTO);

    RelatieDto maakHuwelijk(RelatieDto huwelijk);


    RelatieDto maakHuwelijk(RelatieMetNieuwNatuurlijkPersoonDto huwelijk);
}

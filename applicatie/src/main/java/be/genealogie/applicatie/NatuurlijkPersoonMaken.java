package be.genealogie.applicatie;

import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.dto.NieuwNatuurlijkPersoonDTO;
import be.genealogie.domein.dto.RelatieDto;
import be.genealogie.domein.dto.RelatieMetNieuwNatuurlijkPersoonDto;

public interface NatuurlijkPersoonMaken {
    NatuurlijkPersoonDTO maak(NieuwNatuurlijkPersoonDTO natuurlijkPersoonDTO);

    RelatieDto maakHuwelijk(RelatieDto huwelijk);


    RelatieDto maakHuwelijk(RelatieMetNieuwNatuurlijkPersoonDto huwelijk);
}

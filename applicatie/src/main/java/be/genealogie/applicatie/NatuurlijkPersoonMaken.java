package be.genealogie.applicatie;

import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.dto.NieuwNatuurlijkPersoonDTO;

public interface NatuurlijkPersoonMaken {
    NatuurlijkPersoonDTO maak(NieuwNatuurlijkPersoonDTO natuurlijkPersoonDTO);
}

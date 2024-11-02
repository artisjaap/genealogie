package be.genealogie.applicatie;

import be.genealogie.domein.dto.NatuurlijkPersoonDTO;

import java.util.List;

public interface NatuurlijkPersoonZoeken {
    List<NatuurlijkPersoonDTO> alle();

    List<NatuurlijkPersoonDTO> voorZoekstring(String zoekString);
}

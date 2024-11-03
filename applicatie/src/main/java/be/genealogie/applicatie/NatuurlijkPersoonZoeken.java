package be.genealogie.applicatie;

import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.dto.NatuurlijkPersoonFicheDto;

import java.util.List;

public interface NatuurlijkPersoonZoeken {
    List<NatuurlijkPersoonDTO> alle();

    List<NatuurlijkPersoonDTO> voorZoekstring(String zoekString);

    NatuurlijkPersoonFicheDto ficheVoor(Long id);
}

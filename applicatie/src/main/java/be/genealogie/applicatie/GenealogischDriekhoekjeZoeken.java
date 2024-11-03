package be.genealogie.applicatie;

import be.genealogie.domein.dto.NatuurlijkPersoonDTO;

import java.util.List;
import java.util.Optional;

public interface GenealogischDriekhoekjeZoeken {

    Optional<NatuurlijkPersoonDTO> moederVan(NatuurlijkPersoonDTO persoon);

    Optional<NatuurlijkPersoonDTO> vaderVan(NatuurlijkPersoonDTO persoon);

    List<NatuurlijkPersoonDTO> kinderenVan(NatuurlijkPersoonDTO persoonDto);
}

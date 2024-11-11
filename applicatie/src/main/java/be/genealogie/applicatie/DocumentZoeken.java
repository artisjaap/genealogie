package be.genealogie.applicatie;

import be.genealogie.domein.dto.DocumentContentDto;
import be.genealogie.domein.dto.DocumentHeaderDto;
import be.genealogie.domein.dto.NatuurlijkPersoonDTO;

import java.util.List;
import java.util.Optional;

public interface DocumentZoeken {
    List<DocumentHeaderDto> alleDocumentenVan(NatuurlijkPersoonDTO persoonDto);

    Optional<DocumentContentDto> documentMetId(Long id);
}

package be.genealogie.applicatie;

import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.dto.RelatieDto;

import java.util.List;

public interface ReleatiesZoeken {

    List<RelatieDto> relatiesMet(NatuurlijkPersoonDTO persoonDTO);
}

package be.genealogie.applicatie.authenticatie;

import be.genealogie.domein.dto.authenticatie.GebruikerDto;

import java.util.List;

public interface GebruikersZoeken {

    List<GebruikerDto> alleGebruikers();

    GebruikerDto me();
}

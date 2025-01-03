package be.genealogie.applicatie.authenticatie;

import be.genealogie.domein.GebruikerRole;
import be.genealogie.domein.dto.authenticatie.GebruikerDto;

public interface GebruikersBeheer {
    GebruikerDto voegRoleToeAanGebruiker(Long gebruikerId, GebruikerRole machtiging);
}

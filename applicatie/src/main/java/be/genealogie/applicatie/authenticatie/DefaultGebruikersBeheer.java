package be.genealogie.applicatie.authenticatie;

import be.genealogie.domein.GebruikerRole;
import be.genealogie.domein.dto.authenticatie.GebruikerDto;
import be.genealogie.domein.entiteit.Gebruiker;
import be.genealogie.domein.mapper.ViewMapper;
import be.genealogie.domein.repository.GebruikerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DefaultGebruikersBeheer implements GebruikersBeheer {
    private final GebruikerRepository gebruikerRepository;
    private final ViewMapper viewMapper;

    @Override
    public GebruikerDto voegRoleToeAanGebruiker(Long gebruikerId, GebruikerRole machtiging) {
        Gebruiker gebruiker = gebruikerRepository.getById(gebruikerId);
        gebruiker.voegMachtigingToe(machtiging);
        return viewMapper.map(gebruiker, GebruikerDto.class);
    }
}


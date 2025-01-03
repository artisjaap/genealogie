package be.genealogie.applicatie.authenticatie;

import be.genealogie.domein.dto.authenticatie.GebruikerDto;
import be.genealogie.domein.entiteit.Gebruiker;
import be.genealogie.domein.mapper.ViewMapper;
import be.genealogie.domein.repository.GebruikerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultGebruikersZoeken implements GebruikersZoeken{
    private final GebruikerRepository gebruikerRepository;
    private final ViewMapper viewMapper;

    @Override
    public GebruikerDto me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Gebruiker currentUser = (Gebruiker) authentication.getPrincipal();
        return viewMapper.map(currentUser, GebruikerDto.class);
    }

    @Override
    public List<GebruikerDto> alleGebruikers() {
        List<Gebruiker> gebruikers = gebruikerRepository.findAll();
        return viewMapper.map(gebruikers, GebruikerDto.class);
    }
}

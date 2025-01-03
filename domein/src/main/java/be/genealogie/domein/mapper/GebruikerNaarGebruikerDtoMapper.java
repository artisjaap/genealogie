package be.genealogie.domein.mapper;

import be.genealogie.domein.dto.authenticatie.GebruikerDto;
import be.genealogie.domein.entiteit.Gebruiker;
import be.genealogie.domein.entiteit.Machtiging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GebruikerNaarGebruikerDtoMapper implements Mapper<Gebruiker, GebruikerDto> {


    @Override
    public GebruikerDto map(Gebruiker gebruiker) {
        return GebruikerDto.builder()
                .voornaam(gebruiker.getVoornaam())
                .naam(gebruiker.getNaam())
                .email(gebruiker.getEmail())
                .createdAt(gebruiker.getCreatedAt())
                .updatedAt(gebruiker.getUpdatedAt())
                .machtigingen(gebruiker.getMachtigingen().stream().map(Machtiging::getMachtiging).toList())
                .build();
    }
}
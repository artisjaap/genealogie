package be.genealogie.domein.dto.authenticatie;

import be.genealogie.domein.GebruikerRole;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class GebruikerDto {
    private String voornaam;
    private String naam;
    private String email;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private List<GebruikerRole> machtigingen;
}

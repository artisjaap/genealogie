package be.genealogie.domein.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NatuurlijkPersoonFicheDto {
    private NatuurlijkPersoonDTO moeder;
    private NatuurlijkPersoonDTO vader;
    private NatuurlijkPersoonDTO natuurlijkPersoon;
    private List<RelatieDto> relaties;
    private List<NatuurlijkPersoonDTO> kinderen;
    private List<NatuurlijkPersoonDTO> broersEnZussen;
    private List<NatuurlijkPersoonDTO> nevemEnNichten;
    private List<NatuurlijkPersoonDTO> oomsEnTantes;
    private List<DocumentHeaderDto> documenten;
}

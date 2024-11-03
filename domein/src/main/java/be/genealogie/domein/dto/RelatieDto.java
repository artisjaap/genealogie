package be.genealogie.domein.dto;

import be.genealogie.domein.Geslacht;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatieDto {
    private Long id;
    private NatuurlijkPersoonDTO persoon1;
    private NatuurlijkPersoonDTO persoon2;
    private LocalDate gehuwdOp;
    private String gehuwdTe;
    private List<NatuurlijkPersoonDTO> kinderen;

    public NatuurlijkPersoonDTO getMan() {
        return persoon1.getGeslacht() == Geslacht.MAN? persoon1 : persoon2;
    }

    public NatuurlijkPersoonDTO getVrouw() {
        return persoon1.getGeslacht() == Geslacht.VROUW? persoon1 : persoon2;
    }
}

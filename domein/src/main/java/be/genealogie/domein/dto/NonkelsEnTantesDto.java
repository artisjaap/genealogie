package be.genealogie.domein.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class NonkelsEnTantesDto implements HeeftNatuurlijkPersoonDto {
    private NatuurlijkPersoonDTO natuurlijkPersoon;
    private List<AangetrouwdDto> aangetrouwd;


    @Override
    public NatuurlijkPersoonDTO natuurlijkPersoon() {
        return natuurlijkPersoon;
    }
}

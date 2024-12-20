package be.genealogie.domein.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class AangetrouwdDto implements HeeftNatuurlijkPersoonDto {
    private NatuurlijkPersoonDTO natuurlijkPersoon;
    private boolean actief;


    @Override
    public NatuurlijkPersoonDTO natuurlijkPersoon() {
        return natuurlijkPersoon;
    }
}

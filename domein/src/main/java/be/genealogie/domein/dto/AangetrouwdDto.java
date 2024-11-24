package be.genealogie.domein.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AangetrouwdDto {
    private NatuurlijkPersoonDTO natuurlijkPersoon;
    private boolean actief;
}

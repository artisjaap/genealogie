package be.genealogie.domein.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class NonkelsEnTantesDto {
    private NatuurlijkPersoonDTO natuurlijkPersoon;
    private List<AangetrouwdDto> aangetrouwd;
}

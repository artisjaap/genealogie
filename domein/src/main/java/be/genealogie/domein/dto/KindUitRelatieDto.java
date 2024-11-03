package be.genealogie.domein.dto;

import lombok.Data;

@Data
public class KindUitRelatieDto {
    private RelatieDto huwelijk;
    private NieuwNatuurlijkPersoonDTO kind;
}

package be.genealogie.domein.dto;

import lombok.Data;

@Data
public class KindUitRelatieDto {
    private HuwelijkDto huwelijk;
    private NieuwNatuurlijkPersoonDTO kind;
}

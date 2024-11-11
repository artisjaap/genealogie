package be.genealogie.domein.dto;

import lombok.Data;

@Data
public class KindUitRelatieDto {
    private RelatieDto relatie;
    private NieuwNatuurlijkPersoonDTO kind;
}

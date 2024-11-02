package be.genealogie.domein.dto;

import lombok.Data;

@Data
public class OudersVanKindDto {
    private NieuwNatuurlijkPersoonDTO vader;
    private NieuwNatuurlijkPersoonDTO moeder;
    private NatuurlijkPersoonDTO kind;
}

package be.genealogie.domein.dto;

import lombok.Data;

@Data
public class GenealogischDriehoekjeDTO {
    private NatuurlijkPersoonDTO kind;
    private NatuurlijkPersoonDTO moeder;
    private NatuurlijkPersoonDTO vader;
}

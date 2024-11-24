package be.genealogie.domein.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenealogischDriehoekjeDTO {
    private NatuurlijkPersoonDTO kind;
    private NatuurlijkPersoonDTO ouder1;
    private NatuurlijkPersoonDTO ouder2;
}

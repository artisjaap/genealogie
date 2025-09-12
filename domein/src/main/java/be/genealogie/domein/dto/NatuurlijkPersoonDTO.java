package be.genealogie.domein.dto;

import be.genealogie.domein.Geslacht;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NatuurlijkPersoonDTO implements HeeftNatuurlijkPersoonDto {
    private Long id;
    private String naam;
    private String voornaam;
    private LocalDate geborenOp;
    private String geborenTe;
    private LocalDate overledenOp;
    private String overledenTe;
    private Geslacht geslacht;
    private Integer leeftijd;
    private String sterrenbeeld;

    @Override
    public NatuurlijkPersoonDTO natuurlijkPersoon() {
        return this;
    }
}

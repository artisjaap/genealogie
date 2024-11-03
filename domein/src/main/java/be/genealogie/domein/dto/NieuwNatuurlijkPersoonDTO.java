package be.genealogie.domein.dto;

import be.genealogie.domein.Geslacht;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NieuwNatuurlijkPersoonDTO {
    private String naam;
    private String voornaam;
    private LocalDate geborenOp;
    private String geborenTe;
    private LocalDate overledenOp;
    private String overledenTe;
    private Geslacht geslacht;
}

package be.genealogie.domein.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RelatieMetNieuwNatuurlijkPersoonDto {
    private Long id;
    private NatuurlijkPersoonDTO persoon1;
    private NieuwNatuurlijkPersoonDTO persoon2;
    private LocalDate gehuwdOp;
    private String gehuwdTe;
}

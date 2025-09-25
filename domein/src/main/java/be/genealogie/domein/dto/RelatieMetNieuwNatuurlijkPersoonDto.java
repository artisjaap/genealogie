package be.genealogie.domein.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RelatieMetNieuwNatuurlijkPersoonDto {
    private Long id;
    private NatuurlijkPersoonDTO persoon1;
    private NieuwNatuurlijkPersoonDTO persoon2;
    private LocalDate gehuwdOp;
    private String gehuwdTe;
}

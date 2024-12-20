package be.genealogie.domein.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;

public interface HeeftNatuurlijkPersoonDto {
    NatuurlijkPersoonDTO natuurlijkPersoon();

    static <T extends HeeftNatuurlijkPersoonDto> Comparator<T> sorteerOpGeboortedatum() {
        return Comparator.comparing(natuurlijkPersoon -> Optional.ofNullable(natuurlijkPersoon).map(HeeftNatuurlijkPersoonDto::natuurlijkPersoon).map(NatuurlijkPersoonDTO::getGeborenOp).orElse(LocalDate.MIN));
    }
}

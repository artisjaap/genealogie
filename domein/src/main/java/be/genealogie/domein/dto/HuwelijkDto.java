package be.genealogie.domein.dto;

import java.time.LocalDate;

public record HuwelijkDto(LocalDate huwelijkDatum, String gemeente) {
}

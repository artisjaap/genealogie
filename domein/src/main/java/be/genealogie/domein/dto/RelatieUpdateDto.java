package be.genealogie.domein.dto;

import java.time.LocalDate;

public record RelatieUpdateDto(LocalDate huwelijkDatum, String gemeente, Boolean uitElkaar) {
}

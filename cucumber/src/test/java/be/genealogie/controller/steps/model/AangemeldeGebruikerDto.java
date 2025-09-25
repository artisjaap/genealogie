package be.genealogie.controller.steps.model;

import lombok.Builder;

@Builder
public record AangemeldeGebruikerDto(String username, String password) {
}

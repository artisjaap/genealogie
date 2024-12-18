package be.genealogie.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserResponseDto {
    private String naam;
    private String voornaam;
    private String email;
}

package be.genealogie.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDto {
    private String token;
    private long expiresIn;
    private LoginUserResponseDto gebruiker;


}
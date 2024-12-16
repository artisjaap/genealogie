package be.genealogie.controller.dto;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String email;
    
    private String password;
    
    private String voornaam;
    private String naam;

}
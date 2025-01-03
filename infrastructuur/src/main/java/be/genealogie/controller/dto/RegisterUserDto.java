package be.genealogie.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserDto {
    @Schema(example = "test@gmail.com")
    private String email;

    @Schema(example = "secretpwd")
    private String password;
    
    private String voornaam;
    private String naam;

}
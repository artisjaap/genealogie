package be.genealogie.controller;

import be.genealogie.controller.dto.LoginResponseDto;
import be.genealogie.controller.dto.RegisterUserDto;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class AuthenticationSteps extends SpringIntegrationTest{

    @Autowired
    private AuthenticatieController authenticatieController;

    @Autowired
    private World world;

    @When("Gebruiker meldt zich aan")
    public void gebruiker_meldt_zich_aan() {
        ResponseEntity<LoginResponseDto> register = authenticatieController.register(RegisterUserDto.builder()
                .email("test.email@email.com")
                .naam("naam")
                .voornaam("voornaam")
                .password("password")
                .build());
        world.setToken(register.getBody().getToken());
    }

    @When("Gebruiker heeft een jwt token")
    public void gebruiker_heeft_een_jwt_token() {
        Assertions.assertNotNull(world.getToken());
    }


}

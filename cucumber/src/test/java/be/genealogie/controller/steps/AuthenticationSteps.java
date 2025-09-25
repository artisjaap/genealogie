package be.genealogie.controller.steps;

import be.genealogie.controller.SpringIntegrationTest;
import be.genealogie.controller.dto.LoginResponseDto;
import be.genealogie.controller.dto.LoginUserDto;
import be.genealogie.controller.steps.model.AangemeldeGebruikerDto;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

public class AuthenticationSteps extends SpringIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private World world;

    @When("Gebruiker logged zich in")
    public void gebruiker_meldt_zich_aan() {
        AangemeldeGebruikerDto gebruiker = world.scenarioContext().aangemeldeGebruiker().orElseThrow(() -> new IllegalStateException("Gebruiker moet eerst zijn ingelogd"));
        LoginUserDto loginUserDto = LoginUserDto.builder()
                .email(gebruiker.username())
                .password(gebruiker.password())
                .build();
        ResponseEntity<LoginResponseDto> response = restTemplate.postForEntity("/auth/login", loginUserDto, LoginResponseDto.class);
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        world.setToken(response.getBody().getToken());
    }

    @When("Gebruiker heeft een jwt token")
    public void gebruiker_heeft_een_jwt_token() {
        Assertions.assertNotNull(world.getToken());
    }

}

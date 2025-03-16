package be.genealogie.controller.steps;

import be.genealogie.controller.SpringIntegrationTest;
import be.genealogie.controller.dto.RegisterUserDto;
import be.genealogie.controller.utils.DBUtils;
import be.genealogie.service.AuthenticationService;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationInitialisationSteps extends SpringIntegrationTest {

    @Autowired
    private DBUtils dbUtils;
    @Autowired
    private AuthenticationService authenticationService;

    @When("een lege applicatie met testgebruiker {alfanumeriek}, {alfanumeriek} met email {email} en wachtwoord {alfanumeriek}")
    public void legeApplicatie(String voornaam, String naam, String email, String password) {
        dbUtils.cleanDb();
        authenticationService.signup(RegisterUserDto.builder()
                        .email(email)
                        .password(password)
                        .naam(naam)
                        .voornaam(voornaam)
                .build());
    }
}

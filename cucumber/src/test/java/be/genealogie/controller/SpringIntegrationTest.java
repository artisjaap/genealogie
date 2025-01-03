package be.genealogie.controller;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(properties = {"spring.config.name=genealogie"})
@ActiveProfiles("cucumber")
public class SpringIntegrationTest {

}
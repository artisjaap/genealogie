package be.genealogie.controller;

import be.genealogie.GenealogieApplication;
import be.genealogie.testcontainers.TestcontainersConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.TestPropertySource;

@CucumberContextConfiguration
@SpringBootTest(/*properties = {"spring.config.name=genealogie"},*/ classes = { GenealogieApplication.class, TestcontainersConfiguration.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(properties = {"spring.config.name=genealogie"})
@ActiveProfiles("cucumber")
public class SpringIntegrationTest {

}
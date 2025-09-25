package be.genealogie.controller;

import be.genealogie.controller.steps.World;
import be.genealogie.controller.steps.model.ScenarioContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public abstract class AbstractControllerSteps extends SpringIntegrationTest{
    @Autowired
    protected World world;

    @Autowired
    protected TestRestTemplate restTemplate;

    protected ScenarioContext scenarioContext() {
        return world.scenarioContext();
    }

    protected <T> HttpEntity<T> maakHttpEntity(T type) {
        HttpHeaders headers = new HttpHeaders();
        String token = world.getToken();
        if (token != null && !token.isBlank()) {
            headers.setBearerAuth(token);
        }
        return new HttpEntity<>(type, headers);
    }
}

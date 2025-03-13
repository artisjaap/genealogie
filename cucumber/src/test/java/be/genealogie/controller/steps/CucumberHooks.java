package be.genealogie.controller.steps;


import be.genealogie.controller.SpringIntegrationTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CucumberHooks extends SpringIntegrationTest {
  @Autowired
  private World world;

  @Before
  public void before(Scenario scenario) {
    world.init(scenario);
  }

  @After
  public void after(Scenario scenario) {
    world.stopWebdriver();
  }
}

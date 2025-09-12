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

  @Before("@Browser")
  public void beforeBrowser() {
    world.startBrowserVoor();
  }

  @After("@Browser")
  public void afterBrowser() {
    world.stopBrowser();
  }


  @Before("@Recording")
  public void beforeRecording() {
    world.startRecording();
  }

  @After("@Recording")
  public void afterRecording(Scenario scenario) {
    world.stopRecording(scenario);
  }

  @Before("@BrowserRecording")
  public void beforeBrowserRecording() {
    world.startBrowserVoor();
    world.startRecording();
  }

  @After("@BrowserRecording")
  public void afterBrowserRecording(Scenario scenario) {
    world.stopRecording(scenario);
    world.stopBrowser();
  }
}

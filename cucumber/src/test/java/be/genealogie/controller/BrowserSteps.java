package be.genealogie.controller;

import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class BrowserSteps extends SpringIntegrationTest{
    @Autowired
    private World world;

    @Value("${local.server.port}") // Spring Boot draait op een random poort
    private int port;

    @When("Start chrome op de homepage")
    public void open_brower() throws InterruptedException {
        world.getDriver().get("http://host.containers.internal:" + port);
    }

    @When("Vul username in")
    public void vulUsernameIn() throws InterruptedException {
        world.getDriver().findElement(By.cssSelector("#mat-input-0")).sendKeys("test");
        world.getDriver().findElement(By.cssSelector("#mat-input-1")).sendKeys("test");
        world.getDriver().findElement(By.cssSelector("body > app-root > div > app-login > form > div > div > div > div:nth-child(3) > span")).click();
        Thread.sleep(2000);
    }
}

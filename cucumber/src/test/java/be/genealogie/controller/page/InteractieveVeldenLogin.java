package be.genealogie.controller.page;

import be.genealogie.controller.steps.World;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class InteractieveVeldenLogin extends AbstractInteractiveVelden{


    public InteractieveVeldenLogin(World world){
        super(world);
    }

    @Override
    public WebPagina huidigePagina() {
        return WebPagina.LOGIN;
    }

    @Override
    public WebElement loginLink() {
        return world.getDriver().findElement(By.cssSelector("body > app-root > div > app-login > form > div > div > div > div:nth-child(3) > button"));
    }

    @Override
    public WebElement emailInput() {
        return world.getDriver().findElement(By.cssSelector("#mat-input-0"));
    }

    @Override
    public WebElement registeerLink() {
        return world.getDriver().findElement(By.cssSelector("body > app-root > div > app-login > form > div > div > div > div:nth-child(3) > span"));
    }

    @Override
    public WebElement wachtwoordInput() {
        return world.getDriver().findElement(By.cssSelector("#mat-input-1"));
    }

    @Override
    public WebPagina navigeerMetActie(NavigatieActie actie) {
        return switch (actie) {
            case REGISTREER -> WebPagina.REGISTER;
            case LOGIN -> WebPagina.LOGIN;
        };
    }
}



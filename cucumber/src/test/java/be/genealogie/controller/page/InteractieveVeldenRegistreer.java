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
public class InteractieveVeldenRegistreer extends AbstractInteractiveVelden{

    public InteractieveVeldenRegistreer(World world){
        super(world);
    }

    @Override
    public WebPagina huidigePagina() {
        return WebPagina.REGISTER;
    }

    @Override
    public WebElement emailInput() {
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-registreer/form/div/div/div/div[1]/mat-form-field/div[1]/div/div[2]/input"));
    }

    @Override
    public WebElement achternaamInput() {
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-registreer/form/div/div/div/div[4]/mat-form-field/div[1]/div/div[2]/input"));
    }
    @Override
    public WebElement voornaamInput() {
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-registreer/form/div/div/div/div[3]/mat-form-field/div[1]/div/div[2]/input"));
    }

    @Override
    public WebElement wachtwoordInput() {
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-registreer/form/div/div/div/div[2]/mat-form-field/div[1]/div/div[2]/input"));
    }

    @Override
    public WebElement registeerLink() {
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-registreer/form/div/div/div/div[5]/button"));
    }

    @Override
    public WebPagina navigeerMetActie(NavigatieActie actie) {
        return switch (actie) {
            case REGISTREER -> WebPagina.REGISTER; //FIXME
            default -> throw new UnsupportedOperationException("Actie " +actie+" bestaat niet op pagina " + huidigePagina());
        };
    }
}

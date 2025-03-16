package be.genealogie.controller.page;

import be.genealogie.controller.steps.World;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class InteractieveVeldenZoek extends AbstractInteractiveVelden{

    public InteractieveVeldenZoek(World world){
        super(world);
    }

    @Override
    public WebPagina huidigePagina() {
        return WebPagina.ZOEKEN;
    }

    @Override
    public WebElement zoekenLink() {
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-personen-zoeken/div[1]/div/button[1]"));
    }

    @Override
    public WebElement nieuwPersoonLink() {
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-personen-zoeken/div[1]/div/button[2]"));
    }

    @Override
    public WebElement zoekInput() {
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-personen-zoeken/div[1]/div/input"));
    }

    @Override
    public WebPagina navigeerMetActie(NavigatieActie actie) {
        return switch (actie) {
            case NIEUWE_PERSOON -> WebPagina.NIEUW_PERSOON;
            default -> throw new UnsupportedOperationException("Actie " +actie+" bestaat niet op pagina " + huidigePagina());
        };
    }
}

package be.genealogie.controller.page;

import be.genealogie.controller.steps.World;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class InteractieveVeldenNieuwPersoon extends AbstractInteractiveVelden{

    public InteractieveVeldenNieuwPersoon(World world){
        super(world);
    }

    @Override
    public WebPagina huidigePagina() {
        return WebPagina.NIEUW_PERSOON;
    }

    @Override
    public WebElement opslaanLink() {
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-persoon-nieuw-fiche/form/button"));
    }

    @Override
    public WebElement geborenInput() {
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-persoon-nieuw-fiche/form/div[3]/mat-form-field[1]/div[1]/div/div[2]/input"));
    }

    @Override
    public WebElement overledenInput() {
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-persoon-nieuw-fiche/form/div[3]/mat-form-field[2]/div[1]/div/div[2]/input"));
    }

    @Override
    public WebElement voornaamInput() {
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-persoon-nieuw-fiche/form/div[1]/mat-form-field[1]/div[1]/div/div[2]/input"));
    }

    @Override
    public WebElement achternaamInput() {
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-persoon-nieuw-fiche/form/div[1]/mat-form-field[2]/div[1]/div/div[2]/input"));
    }

    @Override
    public WebElement manRadioButton() {
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-persoon-nieuw-fiche/form/div[2]/mat-radio-group/mat-radio-button[1]"));
    }

    @Override
    public WebElement vrouwRadioButton() {
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-persoon-nieuw-fiche/form/div[2]/mat-radio-group/mat-radio-button[2]"));
    }

    @Override
    public WebPagina navigeerMetActie(NavigatieActie actie) {
        return switch (actie) {
            case OPSLAAN -> WebPagina.NATUURLIJK_PERSOON_FICHE;
            default -> throw new UnsupportedOperationException("Actie " +actie+" bestaat niet op pagina " + huidigePagina());
        };
    }
}

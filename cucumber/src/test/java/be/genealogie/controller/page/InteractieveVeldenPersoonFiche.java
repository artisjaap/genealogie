package be.genealogie.controller.page;

import be.genealogie.controller.steps.World;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class InteractieveVeldenPersoonFiche extends AbstractInteractiveVelden{

    public InteractieveVeldenPersoonFiche(World world) {
        super(world);
    }

    @Override
    public WebPagina huidigePagina() {
        return WebPagina.NATUURLIJK_PERSOON_FICHE;
    }

    @Override
    public WebElement relatieToevoegenLink(){
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-persoon-fiche/div/div[2]/div[3]/span/span"));
    }


    @Override
    public WebElement oudersToevoegenLink(){
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-persoon-fiche/div/div[2]/div[1]/div/div[1]/div[2]/span[3]"));
    }

    @Override
    public WebElement wijzigFicheLink(){
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-persoon-fiche/div/div[2]/div[1]/div/div[1]/div[2]/span[1]"));
    }

    @Override
    public WebElement kindToevoegenAanRelatie(){
        return world.getDriver().findElement(By.xpath("/html/body/app-root/div/app-persoon-fiche/div/div[2]/div[3]/div/div/div[1]/div[3]/span[3]"));
    }



    @Override
    public WebPagina navigeerMetActie(NavigatieActie actie) {
        return switch (actie) {
            case WIJZIG_FICHE -> WebPagina.NATUURLIJK_PERSOON_FICHE_EDITEER;
            case OUDERS_TOEVOEGEN -> WebPagina.NATUURLIJK_PERSOON_FICHE_OUDERS_TOEVOEGEN;
            case PERSOON_TOEVOEGEN_AAN_RELATIE -> WebPagina.NATUURLIJK_PERSOON_FICHE_PERSOON_AAN_RELATIE_TOEVOEGEN;
            case RELATIE_TOEVOEGEN -> WebPagina.NATUURLIJK_PERSOON_FICHE_RELATIE_TOEVOEGEN;
            default -> throw new UnsupportedOperationException("Actie " +actie+" bestaat niet op pagina " + huidigePagina());
        };
    }


}

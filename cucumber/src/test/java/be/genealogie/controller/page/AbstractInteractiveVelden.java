package be.genealogie.controller.page;

import be.genealogie.controller.steps.World;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@RequiredArgsConstructor
public abstract class AbstractInteractiveVelden implements InteractieveVelden {

    protected final World world;

    @Override
    public WebElement registeerLink() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }

    @Override
    public WebElement relatieToevoegenLink() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }

    @Override
    public WebElement oudersToevoegenLink() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }

    @Override
    public WebElement wijzigFicheLink() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }

    @Override
    public WebElement kindToevoegenAanRelatie() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }

    @Override
    public WebElement loginLink() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }

    @Override
    public WebElement zoekenLink() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }

    @Override
    public WebElement nieuwPersoonLink() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }

    @Override
    public WebElement manRadioButton() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }

    @Override
    public WebElement vrouwRadioButton() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }

    @Override
    public WebElement zoekInput() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }

    @Override
    public WebElement emailInput() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }

    @Override
    public WebElement achternaamInput() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }
    @Override
    public WebElement voornaamInput() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }

    @Override
    public WebElement wachtwoordInput() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }


    @Override
    public WebElement opslaanLink() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }

    @Override
    public WebElement geborenInput() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }

    @Override
    public WebElement overledenInput() {
        throw new UnsupportedOperationException("Het element is niet gevonden");
    }


    @Override
    public WebElement menuLinkZoeken() {
        return world.getDriver().findElement(By.cssSelector("body > app-root > div > app-navigatie > div:nth-child(1)"));
    }

    @Override
    public WebElement voorActieLink(LinkActie actie){
        return switch (actie) {
            case NIEUW_PERSOON -> nieuwPersoonLink();
            case OPSLAAN -> opslaanLink();
            case LOGIN -> loginLink();
            case REGISTREER -> registeerLink();
            case RELATIE_TOEVOEGEN -> relatieToevoegenLink();
            default -> throw new UnsupportedOperationException("Actie " + actie + " bestaat niet op pagina " + huidigePagina());
        };
    }
}

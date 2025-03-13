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
    public WebElement loginLink() {
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
    public WebElement menuLinkZoeken() {
        return world.getDriver().findElement(By.cssSelector("body > app-root > div > app-navigatie > div:nth-child(1)"));
    }
}

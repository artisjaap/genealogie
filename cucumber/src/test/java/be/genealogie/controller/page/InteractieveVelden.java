package be.genealogie.controller.page;

import org.openqa.selenium.WebElement;

public interface InteractieveVelden {
    WebPagina huidigePagina();

    WebElement registeerLink();

    WebElement loginLink();

    WebElement emailInput();

    WebElement voornaamInput();

    WebElement achternaamInput();

    WebElement wachtwoordInput();

    WebPagina navigeerMetActie(NavigatieActie actie);

    WebElement menuLinkZoeken();
}

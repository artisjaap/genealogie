package be.genealogie.controller.page;

import org.openqa.selenium.WebElement;

public interface InteractieveVelden {
    WebPagina huidigePagina();

    WebElement registeerLink();

    WebElement loginLink();

    WebElement zoekenLink();

    WebElement nieuwPersoonLink();

    WebElement opslaanLink();

    WebElement relatieToevoegenLink();

    WebElement oudersToevoegenLink();

    WebElement wijzigFicheLink();

    WebElement kindToevoegenAanRelatie();

    WebElement manRadioButton();

    WebElement vrouwRadioButton();

    WebElement zoekInput();

    WebElement emailInput();

    WebElement voornaamInput();

    WebElement achternaamInput();

    WebElement wachtwoordInput();

    WebElement geborenInput();

    WebElement overledenInput();

    WebPagina navigeerMetActie(NavigatieActie actie);

    WebElement voorActieLink(LinkActie actie);

    WebElement menuLinkZoeken();
}

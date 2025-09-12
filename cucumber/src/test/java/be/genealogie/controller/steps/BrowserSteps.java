package be.genealogie.controller.steps;

import be.genealogie.controller.SpringIntegrationTest;
import be.genealogie.controller.page.InteractieveVelden;
import be.genealogie.controller.page.InteractieveVeldenFactory;
import be.genealogie.controller.page.LinkActie;
import be.genealogie.controller.page.NavigatieActie;
import be.genealogie.domein.Geslacht;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class BrowserSteps extends SpringIntegrationTest {
    @Autowired
    private World world;

    @Autowired
    private InteractieveVeldenFactory interactiveVeldenFactory;


    @Value("${local.server.port}") // Spring Boot draait op een random poort
    private int port;

    @When("Start chrome op de homepage")
    public void open_brower()  {
        world.getDriver().get("http://host.containers.internal:" + port);
    }

    @When("Vul username {alfanumeriek} in")
    public void vulUsernameIn(String string)  {
        velden().emailInput().sendKeys(string);
    }


    @When("Klik op registreer link")
    public void klikOpRegistreerLink()  {
        InteractieveVelden velden = velden();
        velden.registeerLink().click();
        world.naarPagina(velden.navigeerMetActie(NavigatieActie.REGISTREER));
    }

    @When("klik op {linkActie} actie")
    public void klikOpActie(LinkActie linkActie)  {
        velden().voorActieLink(linkActie).click();
        world.naarPagina(velden().navigeerMetActie(linkActie.navigatieActie()));
    }

    @When("Klikt op geslacht {geslacht}")
    public void geslachtMan(Geslacht geslacht)  {
        WebElement webElement = geslacht == Geslacht.MAN ?
                velden().manRadioButton() :
                velden().vrouwRadioButton();
        webElement.click();
    }

    @When("Vult {alfanumeriek} in als geboortedatum")
    public void vulGeboortedatumIn(String date){
        velden().geborenInput().sendKeys(date);
    }

    @When("gebruiker {email} logt in met wachtwoord {alfanumeriek}")
    public void gebruikerLogtIn(String email, String wachtwoord){
        vulEmailIn(email);
        vulWachtwoordIn(wachtwoord);
        klikOpActie(LinkActie.LOGIN);
    }

    @When("Vul {email} in als email")
    public void vulEmailIn(String email){
        velden().emailInput().sendKeys(email);
    }

    @When("Vul {alfanumeriek} in als wachtwoord")
    public void vulWachtwoordIn(String wachtwoord){
        velden().wachtwoordInput().sendKeys(wachtwoord);
    }

    @When("Vul {alfanumeriek} in als voornaam")
    public void vulVoornaamIn(String voornaam){
        velden().voornaamInput().sendKeys(voornaam);
    }

    @When("Vul {alfanumeriek} in als achternaam")
    public void vulAchternaamIn(String achternaam){
        velden().achternaamInput().sendKeys(achternaam);
    }

    @When("Klik op menu zoeken")
    public void menuItem(){
        velden().menuLinkZoeken().click();
    }

    private InteractieveVelden velden(){
        return interactiveVeldenFactory.voorPagina(world.huidigePagina());
    }


    @And("wacht {int} seconden")
    public void wachtSeconden(int aantalSeconden) throws InterruptedException {
        Thread.sleep(aantalSeconden * 1000);
    }
}

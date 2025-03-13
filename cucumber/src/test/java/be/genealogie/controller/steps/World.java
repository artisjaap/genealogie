package be.genealogie.controller.steps;

import be.genealogie.controller.page.InteractieveVelden;
import be.genealogie.controller.page.InteractieveVeldenFactory;
import be.genealogie.controller.page.WebPagina;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.VncRecordingContainer;

import java.io.File;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Scope(SCOPE_CUCUMBER_GLUE)
@Component
public class World {


    // 🔹 Gedeeld netwerk aanmaken
    private static final Network network = Network.newNetwork();

    // 🔹 WebDriver-container (Chrome)
    private static final BrowserWebDriverContainer<?> webDriverContainer =
            new BrowserWebDriverContainer<>()
                    .withCapabilities(new ChromeOptions().addArguments("--no-sandbox"))
                    .withNetwork(network)
            //        .withNetworkMode("host")
            ;
                     // WebDriver-container moet in hetzelfde netwerk zitten

    // 🔹 VNC Recording container
    private static final VncRecordingContainer vncRecordingContainer =
            new VncRecordingContainer(webDriverContainer)
                    .withVideoFormat(VncRecordingContainer.VncRecordingFormat.MP4)
                    .withNetwork(network)
                   // .withNetworkMode("host")
                    // Moet in hetzelfde netwerk zitten
            ;


    private String token;
    private Scenario scenario;
    private WebPagina huidigePagina = WebPagina.LOGIN;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void init(Scenario scenario) {
        this.scenario = scenario;

        System.out.println("Initializing World");
        if (!webDriverContainer.isRunning()) {
            System.out.println("Starting World");
            webDriverContainer.start();
            vncRecordingContainer.start();
        }

    }

    public void stopWebdriver() {
        System.out.println("Stopping World");
        if (webDriverContainer.isRunning()) {
            System.out.println("stop World");
            String filename = "./" + scenario.getId() + ".mp4";
            vncRecordingContainer.saveRecordingToFile(new File(filename));
            webDriverContainer.stop();
            vncRecordingContainer.stop();

        }

    }


    public WebDriver getDriver() {
        return webDriverContainer.getWebDriver();
    }

    public WebPagina huidigePagina() {
        return huidigePagina;
    }

    public void naarPagina(WebPagina pagina){
        this.huidigePagina = pagina;
    }
}

package be.genealogie.controller.steps;

import be.genealogie.applicatie.utils.StringUtils;
import be.genealogie.controller.page.InteractieveVelden;
import be.genealogie.controller.page.InteractieveVeldenFactory;
import be.genealogie.controller.page.WebPagina;
import io.cucumber.java.Scenario;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.VncRecordingContainer;
import org.testcontainers.containers.wait.strategy.WaitAllStrategy;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Scope(SCOPE_CUCUMBER_GLUE)
@Component
@Slf4j
public class World {


    private static final Network network = Network.newNetwork();

    private static final BrowserWebDriverContainer<?> webDriverContainer =
            new BrowserWebDriverContainer<>()
                    .withCapabilities(new ChromeOptions().addArguments("--no-sandbox"))
                    .withNetwork(network);

    private static final VncRecordingContainer vncRecordingContainer =
            new VncRecordingContainer(webDriverContainer)
                    .withVideoFormat(VncRecordingContainer.VncRecordingFormat.MP4)
                    .withNetwork(network);

    @Getter
    @Setter
    private String token;
    private WebPagina huidigePagina = WebPagina.LOGIN;


    public void startBrowserVoor() {
        if (!webDriverContainer.isRunning()) {
            webDriverContainer.start();
            webDriverContainer.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        }
    }

    public void startRecording() {
        vncRecordingContainer.start();
    }

    public void stopRecording(Scenario scenario) {
        String filename = maakFilename(scenario);
        vncRecordingContainer.saveRecordingToFile(new File(filename));
        vncRecordingContainer.stop();
    }

    private String maakFilename(Scenario scenario) {
        return StringUtils.geldigeBestandsnaam(String.format("%s@%s-%s.mp4", scenario.getStatus(), LocalDateTime.now(), scenario.getName()));
    }

    public void stopBrowser() {
        if (webDriverContainer.isRunning()) {
            webDriverContainer.stop();
        }
    }

    public WebDriver getDriver() {
        return webDriverContainer.getWebDriver();
    }

    public WebPagina huidigePagina() {
        return huidigePagina;
    }

    public void naarPagina(WebPagina pagina) {
        log.info("Ik ben nu op pagina {}", pagina);
        this.huidigePagina = pagina;
    }
}

package be.genealogie.controller;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.VncRecordingContainer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

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

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @PostConstruct
    public void init() {
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
            vncRecordingContainer.saveRecordingToFile(new File("./test2.mp4"));
            webDriverContainer.stop();
            vncRecordingContainer.stop();

        }



    }


    public WebDriver getDriver() {
        return webDriverContainer.getWebDriver();
    }
}

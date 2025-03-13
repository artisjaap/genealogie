package be.genealogie.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;

import java.io.File;

@Slf4j
public class WebDriverManager implements BeforeEachCallback, AfterEachCallback {

    public BrowserWebDriverContainer<?> webDriverContainer =
            new BrowserWebDriverContainer<>()
                    .withCapabilities(new ChromeOptions())
                    .withRecordingMode(
                            BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL,
                            new File("c:/TMP/"));


    private static WebDriver driver;

    @Override
    public void beforeEach(ExtensionContext context) {
        if (!webDriverContainer.isRunning()) {
            webDriverContainer.start();
        }
        driver = webDriverContainer.getWebDriver();
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (webDriverContainer.isRunning()) {
            webDriverContainer.stop();
        }
    }

    public static WebDriver getWebDriver() {
        return driver;
    }
}

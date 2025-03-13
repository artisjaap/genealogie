package be.genealogie.testcontainers;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.VncRecordingContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
public class VncRecordingTest2 {

    @Container
    public BrowserWebDriverContainer<?> webDriverContainer =
        new BrowserWebDriverContainer<>()
            .withCapabilities(new ChromeOptions())
            .withRecordingMode(
                BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL,
                new File("c:/TMP/"), VncRecordingContainer.VncRecordingFormat.MP4);

    @Test
    void testMetVncOpname() {
        WebDriver driver = webDriverContainer.getWebDriver();
        driver.get("https://www.google.com");
        // Voeg hier uw testlogica toe
    }
}

package be.genealogie.testcontainers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.VncRecordingContainer;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class VncRecordingTest {

    // 🔹 Gedeeld netwerk aanmaken
    private static final Network network = Network.newNetwork();

    // 🔹 WebDriver-container (Chrome)
    private static final BrowserWebDriverContainer<?> webDriverContainer =
            new BrowserWebDriverContainer<>()
                    .withCapabilities(new ChromeOptions())
                    .withNetwork(network); // WebDriver-container moet in hetzelfde netwerk zitten

    // 🔹 VNC Recording container
    private static final VncRecordingContainer vncRecordingContainer =
            new VncRecordingContainer(webDriverContainer)
                    .withVideoFormat(VncRecordingContainer.VncRecordingFormat.MP4)
                    .withNetwork(network) // Moet in hetzelfde netwerk zitten
 ;

    static {
//        vncRecordingContainer.saveRecordingToFile(new File("c:/TMP/"));

    }
    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        webDriverContainer.start();  // Start WebDriver
        vncRecordingContainer.start();  // Start opname
        driver = webDriverContainer.getWebDriver();
    }

    @Test
    void testGoogleHomepage() {
        driver.get("https://www.google.com");
        assertThat(driver.getTitle()).contains("Google"); // Controleer of we op de Google-pagina zitten
    }

    @AfterAll
    static void tearDown() {
        vncRecordingContainer.saveRecordingToFile(new File("c:/TMP/test.mp4"));
        vncRecordingContainer.stop();  // Stop opname
        webDriverContainer.stop();  // Stop WebDriver-container
    }
}

package be.genealogie.testcontainers;

import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.MySQLContainer;


import java.io.File;
import java.util.List;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {
    private static final MySQLContainer<?> MYSQL_DB = new MySQLContainer<>("mysql:latest")
            .withExposedPorts(3306)
            .withReuse(true);


    private static final  BrowserWebDriverContainer<?> webDriverContainer =
            new BrowserWebDriverContainer<>()
                    .withCapabilities(new ChromeOptions())
                    .withRecordingMode(
                            BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL,
                            new File("c:/TMP/"));
    static {
        MYSQL_DB.setPortBindings(List.of("3307:3306")); // Bind 3306 in de container aan 3307 op de host
    }

    @Bean
    @ServiceConnection
    MySQLContainer<?> mysqlContainer() {
        return MYSQL_DB;
    }

    @Bean
    BrowserWebDriverContainer<?> webDriverContainer() {
        return webDriverContainer;
    }



}

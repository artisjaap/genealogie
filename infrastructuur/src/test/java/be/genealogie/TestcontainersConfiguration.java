package be.genealogie;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;


@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {
    private static final MySQLContainer<?> MYSQL_DB = new MySQLContainer<>("mysql:latest")
            .withExposedPorts(3306)
            .withReuse(true);
    static {
        MYSQL_DB.setPortBindings(List.of("3307:3306")); // Bind 3306 in de container aan 3307 op de host
    }
    @Bean
    @ServiceConnection
    MySQLContainer<?> mysqlContainer() {
        return MYSQL_DB;
    }
}

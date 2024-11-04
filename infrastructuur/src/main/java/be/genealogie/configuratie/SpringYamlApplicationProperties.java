package be.genealogie.configuratie;

import be.genealogie.applicatie.ApplicationProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "genealogie")
@Data
public class SpringYamlApplicationProperties implements ApplicationProperties {
    private Document document;


}

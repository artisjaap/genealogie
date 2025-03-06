package be.genealogie.configuratie;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security")
@Data
public class SpringYmlSecurityProperties {
    private Jwt jwt;

    @Data
    public static class Jwt {
        private String secretKey;
        private Long expirationTime;

    }
}



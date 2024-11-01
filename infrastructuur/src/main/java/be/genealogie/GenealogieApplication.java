package be.genealogie;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Collections;
import java.util.Map;

@SpringBootApplication
public class GenealogieApplication {
    public static final String APPLICATION_NAME = "genealogie";


    public static void main(String[] args) {

        new SpringApplicationBuilder(GenealogieApplication.class)
                .sources(GenealogieApplication.class)
                .properties(applicationProperties())
                .run(args);
    }

    public static Map<String, Object> applicationProperties() {
        return Collections.singletonMap("spring.config.name", APPLICATION_NAME);
    }

}

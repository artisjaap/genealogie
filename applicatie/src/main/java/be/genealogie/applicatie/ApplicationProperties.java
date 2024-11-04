package be.genealogie.applicatie;

import lombok.Data;

public interface ApplicationProperties {

    Document getDocument();


    @Data
    class Document {
        private String path;
    }
}

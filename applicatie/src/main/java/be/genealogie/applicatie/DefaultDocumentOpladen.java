package be.genealogie.applicatie;

import be.genealogie.domein.dto.DocumentDto;
import be.genealogie.domein.dto.DocumentToegevoegdDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultDocumentOpladen implements DocumentOpladen{

    @Value("${genealogie.document.path}")
    private String path;

    @Override
    public DocumentToegevoegdDto voegDocumentToe(DocumentDto documentDto) {
        return null;
    }
}

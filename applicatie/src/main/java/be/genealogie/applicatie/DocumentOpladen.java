package be.genealogie.applicatie;

import be.genealogie.domein.dto.DocumentDto;
import be.genealogie.domein.dto.DocumentToegevoegdDto;

public interface DocumentOpladen {
    DocumentToegevoegdDto voegDocumentToe(DocumentDto documentDto);
}

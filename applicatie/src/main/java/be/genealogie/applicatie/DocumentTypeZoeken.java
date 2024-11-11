package be.genealogie.applicatie;

import be.genealogie.domein.dto.DocumentTypeDto;

import java.util.List;

public interface DocumentTypeZoeken {

    List<DocumentTypeDto> alleDocumentTypes();

    DocumentTypeDto getById(Long documentTypeId);
}

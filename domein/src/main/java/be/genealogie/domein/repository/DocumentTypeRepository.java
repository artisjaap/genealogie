package be.genealogie.domein.repository;

import be.genealogie.domein.entiteit.DocumentType;

public interface DocumentTypeRepository {
    DocumentType save(DocumentType documentType);
}

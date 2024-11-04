package be.genealogie.domein.repository;

import be.genealogie.domein.entiteit.DocumentType;

import java.util.List;

public interface DocumentTypeRepository {
    DocumentType save(DocumentType documentType);

    DocumentType getById(Long id);

    List<DocumentType> findAll();
}

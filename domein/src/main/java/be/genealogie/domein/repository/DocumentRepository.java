package be.genealogie.domein.repository;

import be.genealogie.domein.entiteit.Document;

public interface DocumentRepository {
    Document save(Document document);
}

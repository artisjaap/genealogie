package be.genealogie.repository;

import be.genealogie.domein.entiteit.Document;
import be.genealogie.domein.repository.DocumentRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaDocumentRepository extends JpaRepository<Document, Long>, DocumentRepository {

}

package be.genealogie.repository;

import be.genealogie.domein.entiteit.DocumentType;
import be.genealogie.domein.repository.DocumentTypeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaDocumentTypeRepository extends JpaRepository<DocumentType, Long>, DocumentTypeRepository {
}

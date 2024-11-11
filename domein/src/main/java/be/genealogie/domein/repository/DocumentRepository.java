package be.genealogie.domein.repository;

import be.genealogie.domein.entiteit.Document;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.entiteit.Relatie;

import java.util.List;

public interface DocumentRepository {
    Document save(Document document);

    List<Document> findByNatuurlijkPersoon(NatuurlijkPersoon persoon);

    List<Document> findByRelatie(Relatie persoon);

    Document getById(Long id);
}

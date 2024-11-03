package be.genealogie.domein.dto;

import be.genealogie.domein.entiteit.DocumentType;
import lombok.Data;

@Data
public class DocumentToegevoegdDto {
    private Long id;
    private String pathNaarDocument;
    private NatuurlijkPersoonDTO natuurlijkPersoonDTO;
    private RelatieDto relatieDto;
    private DocumentTypeDto documentType;
}

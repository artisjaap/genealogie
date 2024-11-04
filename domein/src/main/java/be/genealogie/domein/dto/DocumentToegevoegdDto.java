package be.genealogie.domein.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentToegevoegdDto {
    private Long id;
    private String pathNaarDocument;
    private NatuurlijkPersoonDTO natuurlijkPersoonDTO;
    private RelatieDto relatieDto;
    private DocumentTypeDto documentType;
}

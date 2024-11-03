package be.genealogie.domein.dto;

import lombok.Data;

@Data
public class DocumentDto {
    private byte[] bytes;
    private String origineleFilename;
    private DocumentTypeDto documentType;
    private RelatieDto relatieDto;
    private NatuurlijkPersoonDTO natuurlijkPersoonDTO;
}

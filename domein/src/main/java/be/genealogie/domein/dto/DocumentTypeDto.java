package be.genealogie.domein.dto;

import lombok.Data;

@Data
public class DocumentTypeDto {
    private Long id;
    private String omschrijving;
    private String code;
}

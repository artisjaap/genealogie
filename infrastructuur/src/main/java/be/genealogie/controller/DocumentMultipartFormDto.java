package be.genealogie.controller;

import be.genealogie.domein.dto.DocumentTypeDto;
import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.dto.RelatieDto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DocumentMultipartFormDto {
    private MultipartFile file;
    private DocumentTypeDto documentType;
    private RelatieDto relatie;
    private NatuurlijkPersoonDTO natuurlijkPersoon;
    private String ref;
}

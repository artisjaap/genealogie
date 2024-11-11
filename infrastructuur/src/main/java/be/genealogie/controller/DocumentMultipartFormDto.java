package be.genealogie.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DocumentMultipartFormDto {
    private MultipartFile file;
    private Long documentTypeId;
    private Long relatieId;
    private Long natuurlijkPersoonId;
}

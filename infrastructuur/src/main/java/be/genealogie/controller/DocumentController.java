package be.genealogie.controller;

import be.genealogie.applicatie.DocumentOpladen;
import be.genealogie.domein.dto.DocumentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/document")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentOpladen documentOpladen;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadFile(@ModelAttribute DocumentMultipartFormDto document) {
        try {
            documentOpladen.voegDocumentToe(DocumentDto.builder()
                    .bytes(document.getFile().getBytes())
                    .origineleFilename(document.getFile().getOriginalFilename())
                    .documentType(document.getDocumentType())
                    .natuurlijkPersoonDTO(document.getNatuurlijkPersoon())
                    .relatieDto(document.getRelatie())
                    .build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return ResponseEntity.ok().build();
    }
}

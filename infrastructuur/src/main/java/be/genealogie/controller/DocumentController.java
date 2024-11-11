package be.genealogie.controller;

import be.genealogie.applicatie.DocumentOpladen;
import be.genealogie.applicatie.DocumentZoeken;
import be.genealogie.domein.dto.DocumentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.io.IOException;

@RestController
@RequestMapping("/api/document")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentOpladen documentOpladen;
    private final DocumentZoeken documentZoeken;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadFile(@ModelAttribute DocumentMultipartFormDto document) {
        try {
            documentOpladen.voegDocumentToe(DocumentDto.builder()
                    .bytes(document.getFile().getBytes())
                    .origineleFilename(document.getFile().getOriginalFilename())
                    .documentTypeId(document.getDocumentTypeId())
                    .natuurlijkPersoonId(document.getNatuurlijkPersoonId())
                    .relatieId(document.getRelatieId())
                    .build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<?> getDocument(@PathVariable long id)  {
        return documentZoeken.documentMetId(id)
                .map(doc -> ResponseEntity.ok()
                        .contentType(toMediaType(doc.getMediaType()))
                        .body(doc.getBytes()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private MediaType toMediaType(be.genealogie.domein.dto.MediaType mediaType) {
        return switch (mediaType) {
            case PNG -> MediaType.IMAGE_PNG;
            case JPG -> MediaType.IMAGE_JPEG;
        };
    }

}

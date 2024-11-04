package be.genealogie.controller;

import be.genealogie.applicatie.DocumentTypeZoeken;
import be.genealogie.domein.dto.DocumentTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/referentie")
@RequiredArgsConstructor
public class ReferenceDataController {

    private final DocumentTypeZoeken documentTypeZoeken;

    @GetMapping("document-types")
    public ResponseEntity<List<DocumentTypeDto>> documentTypes(){
        return ResponseEntity.ok(documentTypeZoeken.alleDocumentTypes());
    }
}

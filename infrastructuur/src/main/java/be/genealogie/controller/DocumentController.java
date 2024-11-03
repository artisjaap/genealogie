package be.genealogie.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

    @PostMapping( consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> uploadFile(@ModelAttribute DocumentMultipartFormDto document) {

        return ResponseEntity.ok().build();
    }
}

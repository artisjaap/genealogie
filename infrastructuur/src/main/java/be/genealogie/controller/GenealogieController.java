package be.genealogie.controller;

import be.genealogie.applicatie.GenealogischDriekhoekjeMaken;
import be.genealogie.domein.dto.GenealogischDriehoekjeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genealogie")
@RequiredArgsConstructor
public class GenealogieController {

    private final GenealogischDriekhoekjeMaken genealogischDriekhoekje;

    @PostMapping
    public ResponseEntity<GenealogischDriehoekjeDTO> maakGenealogischDriehoekje(@RequestBody GenealogischDriehoekjeDTO genealogischDriehoekjeDTO){
        return ResponseEntity.ok(genealogischDriekhoekje.maakDriehoekje(genealogischDriehoekjeDTO));
    }
}

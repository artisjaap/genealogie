package be.genealogie.controller;

import be.genealogie.applicatie.authenticatie.GebruikersBeheer;
import be.genealogie.applicatie.authenticatie.GebruikersZoeken;
import be.genealogie.domein.GebruikerRole;
import be.genealogie.domein.dto.authenticatie.GebruikerDto;
import be.genealogie.domein.entiteit.Gebruiker;
import be.genealogie.domein.entiteit.Machtiging;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gebruikers")
@RequiredArgsConstructor
public class GebruikerController {
    private final GebruikersZoeken gebruikersZoeken;
    private final GebruikersBeheer gebruikersBeheer;



    @GetMapping("/me")
    public ResponseEntity<GebruikerDto> authenticatedUser() {
        GebruikerDto me = gebruikersZoeken.me();
        return ResponseEntity.ok(me);
    }

    @GetMapping("/")
    public ResponseEntity<List<GebruikerDto>> allUsers() {
        List<GebruikerDto> alleGebruikers = gebruikersZoeken.alleGebruikers();
        return ResponseEntity.ok(alleGebruikers);
    }

    @PutMapping("/{id}/{role}")
    public ResponseEntity<GebruikerDto> voegRoleToe(@PathVariable GebruikerRole role, @PathVariable Long id) {
        GebruikerDto gebruikerDto = gebruikersBeheer.voegRoleToeAanGebruiker(id, role);
        return ResponseEntity.ok(gebruikerDto);
    }


}

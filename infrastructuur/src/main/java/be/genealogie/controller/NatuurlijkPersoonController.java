package be.genealogie.controller;

import be.genealogie.applicatie.NatuurlijkPersoonMaken;
import be.genealogie.applicatie.NatuurlijkPersoonWijzigen;
import be.genealogie.applicatie.NatuurlijkPersoonZoeken;
import be.genealogie.domein.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/natuurlijk-persoon")
@RequiredArgsConstructor
@Slf4j
public class NatuurlijkPersoonController {

    private final NatuurlijkPersoonMaken natuurlijkPersoonMaken;
    private final NatuurlijkPersoonZoeken natuurlijkPersoonZoeken;
    private final NatuurlijkPersoonWijzigen natuurlijkPersoonWijzigen;

    @PostMapping
    public ResponseEntity<NatuurlijkPersoonDTO> maakNatuurlijkPersoon(@RequestBody NieuwNatuurlijkPersoonDTO nieuwNatuurlijkPersoonDTO){
        return ResponseEntity.ok(natuurlijkPersoonMaken.maak(nieuwNatuurlijkPersoonDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NatuurlijkPersoonFicheDto> ficheVoor(@PathVariable Long id){
        return ResponseEntity.ok(natuurlijkPersoonZoeken.ficheVoor(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NatuurlijkPersoonDTO> updateNatuurlijkPersoon(@RequestBody NatuurlijkPersoonDTO natuurlijkPersoonDTO){
        return ResponseEntity.ok(natuurlijkPersoonWijzigen.wijzig(natuurlijkPersoonDTO));
    }

    @GetMapping
    public ResponseEntity<List<NatuurlijkPersoonDTO>> alleNatuurlijkePersonen() {
        return ResponseEntity.ok(natuurlijkPersoonZoeken.alle());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("zoek")
    public ResponseEntity<List<NatuurlijkPersoonDTO>> zoekNatuurlijkePersonen(@RequestParam String zoekString, Authentication authentication) {
        log.info("Login" + authentication.getName());
        return ResponseEntity.ok(natuurlijkPersoonZoeken.voorZoekstring(zoekString));
    }



}

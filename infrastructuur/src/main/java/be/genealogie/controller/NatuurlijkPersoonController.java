package be.genealogie.controller;

import be.genealogie.applicatie.NatuurlijkPersoonMaken;
import be.genealogie.applicatie.NatuurlijkPersoonZoeken;
import be.genealogie.domein.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/natuurlijk-persoon")
@RequiredArgsConstructor
public class NatuurlijkPersoonController {

    private final NatuurlijkPersoonMaken natuurlijkPersoonMaken;
    private final NatuurlijkPersoonZoeken natuurlijkPersoonZoeken;

    @PostMapping
    public ResponseEntity<NatuurlijkPersoonDTO> maakNatuurlijkPersoon(@RequestBody NieuwNatuurlijkPersoonDTO nieuwNatuurlijkPersoonDTO){
        return ResponseEntity.ok(natuurlijkPersoonMaken.maak(nieuwNatuurlijkPersoonDTO));
    }


    @PostMapping("/huwelijk")
    public ResponseEntity<HuwelijkDto> maakHuwelijkTussenTweeBestaandeNatuurlijkePersonen(@RequestBody HuwelijkDto huwelijk){
        return ResponseEntity.ok(natuurlijkPersoonMaken.maakHuwelijk(huwelijk));
    }

    @PostMapping("/huwelijk-nieuw")
    public ResponseEntity<HuwelijkDto> maakHuwelijkTussenMetNieuwNatuurlijkePersoon(@RequestBody HuwelijkMetNieuwNatuurlijkPersoonDto huwelijk){
        return ResponseEntity.ok(natuurlijkPersoonMaken.maakHuwelijk(huwelijk));
    }


    @GetMapping
    public ResponseEntity<List<NatuurlijkPersoonDTO>> alleNatuurlijkePersonen() {
        return ResponseEntity.ok(natuurlijkPersoonZoeken.alle());
    }

    @GetMapping("zoek/{zoekString}")
    public ResponseEntity<List<NatuurlijkPersoonDTO>> zoekNatuurlijkePersonen(@PathVariable String zoekString) {
        return ResponseEntity.ok(natuurlijkPersoonZoeken.voorZoekstring(zoekString));
    }



}

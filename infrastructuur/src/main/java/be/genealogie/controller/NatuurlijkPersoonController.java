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

    @GetMapping("/{id}")
    public ResponseEntity<NatuurlijkPersoonFicheDto> ficheVoor(@PathVariable Long id){
        return ResponseEntity.ok(natuurlijkPersoonZoeken.ficheVoor(id));
    }

//    @PostMapping("/huwelijk")
//    public ResponseEntity<RelatieDto> maakHuwelijkTussenTweeBestaandeNatuurlijkePersonen(@RequestBody RelatieDto huwelijk){
//        return ResponseEntity.ok(natuurlijkPersoonMaken.maakHuwelijk(huwelijk));
//    }
//
//    @PostMapping("/huwelijk-nieuw")
//    public ResponseEntity<RelatieDto> maakHuwelijkTussenMetNieuwNatuurlijkePersoon(@RequestBody RelatieMetNieuwNatuurlijkPersoonDto huwelijk){
//        return ResponseEntity.ok(natuurlijkPersoonMaken.maakHuwelijk(huwelijk));
//    }


    @GetMapping
    public ResponseEntity<List<NatuurlijkPersoonDTO>> alleNatuurlijkePersonen() {
        return ResponseEntity.ok(natuurlijkPersoonZoeken.alle());
    }

    @GetMapping("zoek")
    public ResponseEntity<List<NatuurlijkPersoonDTO>> zoekNatuurlijkePersonen(@RequestParam String zoekString) {
        return ResponseEntity.ok(natuurlijkPersoonZoeken.voorZoekstring(zoekString));
    }



}

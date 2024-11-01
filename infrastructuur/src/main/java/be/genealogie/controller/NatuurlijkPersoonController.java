package be.genealogie.controller;

import be.genealogie.applicatie.NatuurlijkPersoonMaken;
import be.genealogie.applicatie.NatuurlijkPersoonZoeken;
import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.dto.NieuwNatuurlijkPersoonDTO;
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

    @GetMapping
    public ResponseEntity<List<NatuurlijkPersoonDTO>> alleNatuurlijkePersonen() {
        return ResponseEntity.ok(natuurlijkPersoonZoeken.alle());
    }


}

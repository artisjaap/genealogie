package be.genealogie.controller;

import be.genealogie.applicatie.GenealogischDriekhoekjeMaken;
import be.genealogie.applicatie.NatuurlijkPersoonMaken;
import be.genealogie.applicatie.RelatieWijzigen;
import be.genealogie.applicatie.StamboomMaken;
import be.genealogie.domein.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genealogie")
@RequiredArgsConstructor
public class GenealogieController {

    private final GenealogischDriekhoekjeMaken genealogischDriekhoekje;
    private final NatuurlijkPersoonMaken relatieRegistratie;
    private final RelatieWijzigen relatieWijzigen;
    private final StamboomMaken stamboomMaken;

    @PostMapping
    public ResponseEntity<GenealogischDriehoekjeDTO> maakGenealogischDriehoekje(@RequestBody GenealogischDriehoekjeDTO genealogischDriehoekjeDTO){
        return ResponseEntity.ok(genealogischDriekhoekje.maakDriehoekje(genealogischDriehoekjeDTO));
    }

    @PostMapping("/kind-uit-relatie")
    public ResponseEntity<GenealogischDriehoekjeDTO> registreerKindUitRelatie(@RequestBody KindUitRelatieDto kindUitRelatie){
        return ResponseEntity.ok(genealogischDriekhoekje.registreerKindUitRelatie(kindUitRelatie));
    }

    @PostMapping("/registreerd-ouders")
    public ResponseEntity<GenealogischDriehoekjeDTO> registreerOudersVanPersoon(@RequestBody OudersVanKindDto oudersVanKind){
        return ResponseEntity.ok(genealogischDriekhoekje.registreerdOudersVanKind(oudersVanKind));
    }

    @PostMapping("/registreerd-relatie-met")
    public ResponseEntity<RelatieDto> registreerRelatieMet(@RequestBody RelatieMetNieuwNatuurlijkPersoonDto relatieMet){
        return ResponseEntity.ok(relatieRegistratie.maakHuwelijk(relatieMet));
    }

    @PutMapping("/relatie/{id}")
    public ResponseEntity<RelatieDto> huwelijkToevoegen(@PathVariable Long id, @RequestBody RelatieUpdateDto huwelijk){
        return ResponseEntity.ok(relatieWijzigen.wijzigRelatie(id, huwelijk));
    }

//    @PutMapping("/relatie/{id}/scheiding")
//    public ResponseEntity<RelatieDto> scheidingToevoegen(@PathVariable Long id, @RequestBody ScheidingDto scheiding){
//        return ResponseEntity.ok(relatieWijzigen.voegScheidingToe(id, scheiding));
//    }

    @GetMapping("/nakomelingen/{persoonId}")
    public ResponseEntity<StamboomDto> nakomelingenVanPersoon(@PathVariable Long persoonId){
        return ResponseEntity.ok(stamboomMaken.nakomelingenVan(persoonId));
    }

    @GetMapping("/voorouders/{persoonId}")
    public ResponseEntity<StamboomDto> vooroudersVanPersoon(@PathVariable Long persoonId){
        return ResponseEntity.ok(stamboomMaken.vooroudersVan(persoonId));
    }

}

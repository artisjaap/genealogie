package be.genealogie.controller;

import be.genealogie.applicatie.GenealogischDriekhoekjeMaken;
import be.genealogie.applicatie.NatuurlijkPersoonMaken;
import be.genealogie.domein.dto.*;
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
    private final NatuurlijkPersoonMaken relatieRegistratie;

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


}

package be.genealogie.controller.steps.model;

import be.genealogie.domein.dto.GenealogischDriehoekjeDTO;
import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.dto.RelatieDto;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
public class ScenarioContext {
    private AangemeldeGebruikerDto aangemeldeGebruiker;
    private NatuurlijkPersoonDTO laatsteToegevoegdePersoon;
    private RelatieDto laatsteToegevoegdeRelatie;
    private Map<String, NatuurlijkPersoonDTO> allePersonen = new HashMap<>();

    public Optional<AangemeldeGebruikerDto> aangemeldeGebruiker(){
        return Optional.ofNullable(aangemeldeGebruiker);
    }

    public Optional<NatuurlijkPersoonDTO> laatsteToegevoegdePersoon(){
        return Optional.ofNullable(laatsteToegevoegdePersoon);
    }
    public Optional<RelatieDto> laatsteToegevoegdeRelatie(){
        return Optional.ofNullable(laatsteToegevoegdeRelatie);
    }

    public void updateAangemeldeGebruiker(AangemeldeGebruikerDto aangemeldeGebruikerDto) {
        this.aangemeldeGebruiker = aangemeldeGebruikerDto;
    }
    public void updateLaatsteToegevoegdePersoon(String referentie, NatuurlijkPersoonDTO laatsteToegevoegdePersoon) {
        this.laatsteToegevoegdePersoon = laatsteToegevoegdePersoon;
        this.allePersonen.put(referentie, laatsteToegevoegdePersoon);
    }

    public void updateLaatsteToegevoegdeRelatie(String referentie, RelatieDto laatsteToegevoegdeRelatie) {
        this.laatsteToegevoegdeRelatie = laatsteToegevoegdeRelatie;
        this.allePersonen.put(referentie, laatsteToegevoegdeRelatie.getPersoon2());
    }

    public void updateLaatsteToegevoegdeKind(String referentie, GenealogischDriehoekjeDTO body) {
        this.allePersonen.put(referentie, body.getKind());
    }

    public Map<String, NatuurlijkPersoonDTO> alleAangemaaktePersonen() {
        return this.allePersonen;
    }

    public Optional<NatuurlijkPersoonDTO> natuurlijkPersoonMetReferentie(String persoon1Ref) {
        return Optional.ofNullable(this.allePersonen.get(persoon1Ref));
    }
}

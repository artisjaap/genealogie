package be.genealogie.controller.steps;

import be.genealogie.controller.AbstractControllerSteps;
import be.genealogie.domein.Geslacht;
import be.genealogie.domein.dto.GenealogischDriehoekjeDTO;
import be.genealogie.domein.dto.KindUitRelatieDto;
import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.dto.NieuwNatuurlijkPersoonDTO;
import be.genealogie.domein.dto.RelatieDto;
import be.genealogie.domein.dto.RelatieMetNieuwNatuurlijkPersoonDto;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public class GenealogieControllerSteps  extends AbstractControllerSteps {

    @When("een natuurlijk persoon {referentie} {alfanumeriek}, {alfanumeriek}, {geslacht} geboren op {datum} te {alfanumeriek}, overleden op {datum} te {alfanumeriek} wordt toegevoegd")
    public void natuurlijkPersoonToevoegen(String referentie, String naam, String voornaam, Geslacht geslacht, LocalDate geborenOp, String geborenTe, LocalDate overledenOp, String overledenTe) {
        NieuwNatuurlijkPersoonDTO natuurlijkPersoonDTO = NieuwNatuurlijkPersoonDTO.builder()
                .naam(naam)
                .voornaam(voornaam)
                .geborenOp(geborenOp)
                .geborenTe(geborenTe)
                .overledenOp(overledenOp)
                .overledenTe(overledenTe)
                .geslacht(geslacht)
                .build();

        HttpEntity<NieuwNatuurlijkPersoonDTO> request = maakHttpEntity(natuurlijkPersoonDTO);

        ResponseEntity<NatuurlijkPersoonDTO> response = restTemplate.postForEntity("/api/natuurlijk-persoon", request, NatuurlijkPersoonDTO.class);
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        scenarioContext().updateLaatsteToegevoegdePersoon(referentie, response.getBody());
    }

    @When("gehuwd met {referentie} {alfanumeriek}, {alfanumeriek}, {geslacht} op {datum} te {alfanumeriek} die geboren is op {datum} te {alfanumeriek}, overleden op {datum} te {alfanumeriek}")
    public void gehuwdMet(String referentie, String naam, String voornaam, Geslacht geslacht, LocalDate gehuwdOp, String gehuwdTe, LocalDate geborenOp, String geborenTe, LocalDate overledenOp, String overledenTe){
        RelatieMetNieuwNatuurlijkPersoonDto relatieMet = RelatieMetNieuwNatuurlijkPersoonDto.builder()
                .persoon1(scenarioContext().laatsteToegevoegdePersoon().orElseThrow())
                .persoon2(NieuwNatuurlijkPersoonDTO.builder()
                        .naam(naam)
                        .voornaam(voornaam)
                        .geborenOp(geborenOp)
                        .geborenTe(geborenTe)
                        .overledenOp(overledenOp)
                        .overledenTe(overledenTe)
                        .geslacht(geslacht)
                        .build())
                .gehuwdOp(gehuwdOp)
                .gehuwdTe(gehuwdTe)
                .build();
        HttpEntity<RelatieMetNieuwNatuurlijkPersoonDto> request = maakHttpEntity(relatieMet);

        ResponseEntity<RelatieDto> response = restTemplate.postForEntity("/api/genealogie/registreerd-relatie-met", request, RelatieDto.class);
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        scenarioContext().updateLaatsteToegevoegdeRelatie(referentie, response.getBody());
    }

    @When("en natuurlijk persoon {referentie} gehuwd met {referentie} {alfanumeriek}, {alfanumeriek}, {geslacht} op {datum} te {alfanumeriek}, die geboren is op {datum} te {alfanumeriek}, overleden op {datum} te {alfanumeriek}")
    public void persoonGehuwdMet(String persoon1Ref, String referentie, String naam, String voornaam, Geslacht geslacht, LocalDate gehuwdOp, String gehuwdTe, LocalDate geborenOp, String geborenTe, LocalDate overledenOp, String overledenTe){
        RelatieMetNieuwNatuurlijkPersoonDto relatieMet = RelatieMetNieuwNatuurlijkPersoonDto.builder()
                .persoon1(scenarioContext().natuurlijkPersoonMetReferentie(persoon1Ref).orElseThrow())
                .persoon2(NieuwNatuurlijkPersoonDTO.builder()
                        .naam(naam)
                        .voornaam(voornaam)
                        .geborenOp(geborenOp)
                        .geborenTe(geborenTe)
                        .overledenOp(overledenOp)
                        .overledenTe(overledenTe)
                        .geslacht(geslacht)
                        .build())
                .gehuwdOp(gehuwdOp)
                .gehuwdTe(gehuwdTe)
                .build();
        HttpEntity<RelatieMetNieuwNatuurlijkPersoonDto> request = maakHttpEntity(relatieMet);

        ResponseEntity<RelatieDto> response = restTemplate.postForEntity("/api/genealogie/registreerd-relatie-met", request, RelatieDto.class);
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        scenarioContext().updateLaatsteToegevoegdeRelatie(referentie, response.getBody());
    }

    @When("kind {referentie} met {alfanumeriek}, {alfanumeriek}, {geslacht} geboren op {datum} te {alfanumeriek}, overleden op {datum} te {alfanumeriek}")
    public void kindVoorLaatsteRelatie(String referentie, String naam, String voornaam, Geslacht geslacht, LocalDate geborenOp, String geborenTe, LocalDate overledenOp, String overledenTe){
        KindUitRelatieDto kindUitRelatieDto = KindUitRelatieDto.builder()
                .relatie(scenarioContext().laatsteToegevoegdeRelatie().orElseThrow())
                .kind(NieuwNatuurlijkPersoonDTO.builder()
                        .naam(naam)
                        .voornaam(voornaam)
                        .geborenOp(geborenOp)
                        .geborenTe(geborenTe)
                        .overledenOp(overledenOp)
                        .overledenTe(overledenTe)
                        .geslacht(geslacht)
                        .build())
                .build();
        HttpEntity<KindUitRelatieDto> request = maakHttpEntity(kindUitRelatieDto);

        ResponseEntity<GenealogischDriehoekjeDTO> response = restTemplate.postForEntity("/api/genealogie/kind-uit-relatie", request, GenealogischDriehoekjeDTO.class);
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        scenarioContext().updateLaatsteToegevoegdeKind(referentie, response.getBody());

    }

    @Then("overzicht alle personen")
    public void overzichtAllePersonen(){
        scenarioContext().alleAangemaaktePersonen().forEach((referentie, persoon) -> {
            System.out.println(referentie + " " + persoon.getNaam() + " " + persoon.getVoornaam());
        });


    }
}

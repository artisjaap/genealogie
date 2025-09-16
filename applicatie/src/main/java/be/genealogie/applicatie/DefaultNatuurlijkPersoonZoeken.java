package be.genealogie.applicatie;

import be.genealogie.applicatie.mapper.NatuurlijkPersoonMapper;
import be.genealogie.domein.dto.*;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultNatuurlijkPersoonZoeken implements NatuurlijkPersoonZoeken {
    private final GenealogischDriekhoekjeZoeken genealogischDriekhoekjeZoeken;
    private final ReleatiesZoeken releatiesZoeken;
    private final DocumentZoeken documentZoeken;
    private final NatuurlijkPersoonRepository natuurlijkPersoonRepository;


    @Override
    public List<NatuurlijkPersoonDTO> alle() {
        List<NatuurlijkPersoon> all = natuurlijkPersoonRepository.findAll();
        return NatuurlijkPersoonMapper.mapLijst(all);
    }

    @Override
    public List<NatuurlijkPersoonDTO> voorZoekstring(String zoekString) {
        List<NatuurlijkPersoon> all = natuurlijkPersoonRepository.findByNaamLikeOrVoornaamLike(zoekString);
        return NatuurlijkPersoonMapper.mapLijst(all);
    }

    @Override
    public NatuurlijkPersoonFicheDto ficheVoor(Long persoonId) {
        NatuurlijkPersoonDTO persoonDto = natuurlijkPersoonDTO(persoonId);
        NatuurlijkPersoonDTO moeder = genealogischDriekhoekjeZoeken.moederVan(persoonDto).orElse(null);
        NatuurlijkPersoonDTO vader = genealogischDriekhoekjeZoeken.vaderVan(persoonDto).orElse(null);
        List<RelatieDto> relaties = releatiesZoeken.relatiesMet(persoonDto);
        List<NatuurlijkPersoonDTO> kinderen = genealogischDriekhoekjeZoeken.kinderenVan(persoonDto);
        List<BroerOfZusDto> broersEnZussen = genealogischDriekhoekjeZoeken.broersEnZussenVan(persoonDto);
        List<NatuurlijkPersoonDTO> nevenEnNichten = genealogischDriekhoekjeZoeken.nevenEnNichtenVan(persoonDto);
        List<NonkelsEnTantesDto> nonkelsEnTantes = genealogischDriekhoekjeZoeken.nonkelsEnTantes(persoonDto);
        List<DocumentHeaderDto> documenten = documentZoeken.alleDocumentenVan(persoonDto);

        return NatuurlijkPersoonFicheDto.builder()
                .natuurlijkPersoon(persoonDto)
                .vader(vader)
                .moeder(moeder)
                .relaties(relaties)
                .kinderen(kinderen)
                .broersEnZussen(broersEnZussen)
                .nevenEnNichten(nevenEnNichten)
                .nonkelsEnTantes(nonkelsEnTantes)
                .documenten(documenten)
                .build();
    }

    private NatuurlijkPersoonDTO natuurlijkPersoonDTO(Long persoonId) {
        NatuurlijkPersoon persoon = natuurlijkPersoonRepository.getById(persoonId);
        return NatuurlijkPersoonMapper.map(persoon);
    }



}

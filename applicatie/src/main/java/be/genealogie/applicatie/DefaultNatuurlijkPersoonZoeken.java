package be.genealogie.applicatie;

import be.genealogie.domein.dto.DocumentHeaderDto;
import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.dto.NatuurlijkPersoonFicheDto;
import be.genealogie.domein.dto.RelatieDto;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultNatuurlijkPersoonZoeken implements NatuurlijkPersoonZoeken {
    private final GenealogischDriekhoekjeZoeken genealogischDriekhoekjeZoeken;
    private final ReleatiesZoeken releatiesZoeken;
    private final DocumentZoeken documentZoeken;
    private final NatuurlijkPersoonRepository natuurlijkPersoonRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<NatuurlijkPersoonDTO> alle() {
        List<NatuurlijkPersoon> all = natuurlijkPersoonRepository.findAll();
        return mapLijst(all);
    }

    @Override
    public List<NatuurlijkPersoonDTO> voorZoekstring(String zoekString) {
        List<NatuurlijkPersoon> all = natuurlijkPersoonRepository.findByNaamLikeOrVoornaamLike(zoekString);
        return mapLijst(all);
    }

    @Override
    public NatuurlijkPersoonFicheDto ficheVoor(Long persoonId) {
        NatuurlijkPersoonDTO persoonDto = natuurlijkPersoonDTO(persoonId);
        NatuurlijkPersoonDTO moeder = genealogischDriekhoekjeZoeken.moederVan(persoonDto).orElse(null);
        NatuurlijkPersoonDTO vader = genealogischDriekhoekjeZoeken.vaderVan(persoonDto).orElse(null);
        List<RelatieDto> relaties = releatiesZoeken.relatiesMet(persoonDto);
        List<NatuurlijkPersoonDTO> kinderen = genealogischDriekhoekjeZoeken.kinderenVan(persoonDto);
        List<NatuurlijkPersoonDTO> broersEnZussen = genealogischDriekhoekjeZoeken.broersEnZussenVan(persoonDto);
        List<NatuurlijkPersoonDTO> nevenEnNichten = genealogischDriekhoekjeZoeken.nevenEnNichtenVan(persoonDto);
        List<NatuurlijkPersoonDTO> oomsEnTantes = genealogischDriekhoekjeZoeken.oomsEnTantesVan(persoonDto);
        List<DocumentHeaderDto> documenten = documentZoeken.alleDocumentenVan(persoonDto);

        return NatuurlijkPersoonFicheDto.builder()
                .natuurlijkPersoon(persoonDto)
                .vader(vader)
                .moeder(moeder)
                .relaties(relaties)
                .kinderen(kinderen)
                .broersEnZussen(broersEnZussen)
                .nevemEnNichten(nevenEnNichten)
                .oomsEnTantes(oomsEnTantes)
                .documenten(documenten)
                .build();
    }

    private NatuurlijkPersoonDTO natuurlijkPersoonDTO(Long persoonId) {
        NatuurlijkPersoon persoon = natuurlijkPersoonRepository.getById(persoonId);
        NatuurlijkPersoonDTO persoonDto = modelMapper.map(persoon, NatuurlijkPersoonDTO.class);
        return persoonDto;
    }


    private List<NatuurlijkPersoonDTO> mapLijst(List<NatuurlijkPersoon> all) {
        return all.stream().map(e -> modelMapper.map(e, NatuurlijkPersoonDTO.class)).toList();
    }
}

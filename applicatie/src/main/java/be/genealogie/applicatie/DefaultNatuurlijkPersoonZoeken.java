package be.genealogie.applicatie;

import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultNatuurlijkPersoonZoeken implements NatuurlijkPersoonZoeken {
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

    private List<NatuurlijkPersoonDTO> mapLijst(List<NatuurlijkPersoon> all) {
        return all.stream().map(e -> modelMapper.map(e, NatuurlijkPersoonDTO.class)).toList();
    }
}

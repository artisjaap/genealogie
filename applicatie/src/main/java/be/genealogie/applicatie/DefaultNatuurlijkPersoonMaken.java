package be.genealogie.applicatie;

import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.dto.NieuwNatuurlijkPersoonDTO;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DefaultNatuurlijkPersoonMaken implements NatuurlijkPersoonMaken {
    private final ModelMapper modelMapper;
    private final NatuurlijkPersoonRepository natuurlijkPersoonRepository;

    @Override
    public NatuurlijkPersoonDTO maak(NieuwNatuurlijkPersoonDTO natuurlijkPersoonDTO) {
        NatuurlijkPersoon natuurlijkPersoon = modelMapper.map(natuurlijkPersoonDTO, NatuurlijkPersoon.class);
        NatuurlijkPersoon gewijzigdNatuurlijkPersoon = natuurlijkPersoonRepository.save(natuurlijkPersoon);
        return modelMapper.map(gewijzigdNatuurlijkPersoon, NatuurlijkPersoonDTO.class);
    }
}

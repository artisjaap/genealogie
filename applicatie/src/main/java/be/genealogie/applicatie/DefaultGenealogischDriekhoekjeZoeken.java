package be.genealogie.applicatie;

import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.entiteit.GenealogischDriekhoekje;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.repository.GenealogischDriekhoekjeRepository;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DefaultGenealogischDriekhoekjeZoeken implements GenealogischDriekhoekjeZoeken {
    private final GenealogischDriekhoekjeRepository genealogischDriekhoekjeRepository;
    private final NatuurlijkPersoonRepository persoonRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<NatuurlijkPersoonDTO> moederVan(NatuurlijkPersoonDTO persoon) {
        NatuurlijkPersoon persoonDb = persoonRepository.getById(persoon.getId());
        return genealogischDriekhoekjeRepository.findByKind(persoonDb)
                .map(GenealogischDriekhoekje::getMoeder)
                .map(moeder -> modelMapper.map(moeder, NatuurlijkPersoonDTO.class));
    }

    @Override
    public Optional<NatuurlijkPersoonDTO> vaderVan(NatuurlijkPersoonDTO persoon) {
        NatuurlijkPersoon persoonDb = persoonRepository.getById(persoon.getId());
        return genealogischDriekhoekjeRepository.findByKind(persoonDb)
                .map(GenealogischDriekhoekje::getVader)
                .map(vader -> modelMapper.map(vader, NatuurlijkPersoonDTO.class));
    }

    @Override
    public List<NatuurlijkPersoonDTO> kinderenVan(NatuurlijkPersoonDTO persoon) {
        NatuurlijkPersoon persoonDb = persoonRepository.getById(persoon.getId());
        return genealogischDriekhoekjeRepository.findByMoederOrVader(persoonDb, persoonDb)
                .stream()
                .map(GenealogischDriekhoekje::getKind)
                .map(kind -> modelMapper.map(kind, NatuurlijkPersoonDTO.class))
                .toList();
    }
}

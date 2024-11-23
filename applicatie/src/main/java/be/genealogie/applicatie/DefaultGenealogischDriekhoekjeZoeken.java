package be.genealogie.applicatie;

import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.entiteit.GenealogischDriekhoekje;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.entiteit.Relatie;
import be.genealogie.domein.repository.GenealogischDriekhoekjeRepository;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import be.genealogie.domein.repository.RelatieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultGenealogischDriekhoekjeZoeken implements GenealogischDriekhoekjeZoeken {
    private final GenealogischDriekhoekjeRepository genealogischDriekhoekjeRepository;
    private final NatuurlijkPersoonRepository persoonRepository;
    private final RelatieRepository relatieRepository;
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

    @Override
    public List<NatuurlijkPersoonDTO> broersEnZussenVan(NatuurlijkPersoonDTO persoon) {
        NatuurlijkPersoon persoonDb = persoonRepository.getById(persoon.getId());
        Optional<GenealogischDriekhoekje> driehoekjeOptional = genealogischDriekhoekjeRepository.findByKind(persoonDb);
        return driehoekjeOptional.map(driehoekje -> genealogischDriekhoekjeRepository.findByMoederAndVader(driehoekje.getMoeder(), driehoekje.getVader())
                    .stream()
                    .map(GenealogischDriekhoekje::getKind)
                    .filter(p -> !Objects.equals(persoon.getId(), p.getId()))
                    .map(p -> modelMapper.map(p, NatuurlijkPersoonDTO.class))
                    .collect(Collectors.toList())).orElseGet(List::of);
    }

    @Override
    public List<NatuurlijkPersoonDTO> nevenEnNichtenVan(NatuurlijkPersoonDTO persoon) {
        return List.of();
    }

    @Override
    public List<NatuurlijkPersoonDTO> oomsEnTantesVan(NatuurlijkPersoonDTO persoon) {
        return List.of();
    }
}

package be.genealogie.applicatie;

import be.genealogie.domein.dto.BroerOfZusDto;
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
                .map(GenealogischDriekhoekje::moeder)
                .map(moeder -> modelMapper.map(moeder, NatuurlijkPersoonDTO.class));
    }

    @Override
    public Optional<NatuurlijkPersoonDTO> vaderVan(NatuurlijkPersoonDTO persoon) {
        NatuurlijkPersoon persoonDb = persoonRepository.getById(persoon.getId());
        return genealogischDriekhoekjeRepository.findByKind(persoonDb)
                .map(GenealogischDriekhoekje::vader)
                .map(vader -> modelMapper.map(vader, NatuurlijkPersoonDTO.class));
    }

    @Override
    public List<NatuurlijkPersoonDTO> kinderenVan(NatuurlijkPersoonDTO persoon) {
        NatuurlijkPersoon persoonDb = persoonRepository.getById(persoon.getId());
        return genealogischDriekhoekjeRepository.zoekOpOuder(persoonDb)
                .stream()
                .map(GenealogischDriekhoekje::getKind)
                .map(kind -> modelMapper.map(kind, NatuurlijkPersoonDTO.class))
                .toList();
    }

    @Override
    public List<BroerOfZusDto> broersEnZussenVan(NatuurlijkPersoonDTO persoon) {
        NatuurlijkPersoon persoonDb = persoonRepository.getById(persoon.getId());
        Optional<GenealogischDriekhoekje> driehoekjeOptional = genealogischDriekhoekjeRepository.findByKind(persoonDb);
        return driehoekjeOptional.map(driehoekje -> genealogischDriekhoekjeRepository.zoekOpEenVanDeOuders(driehoekje.getOuder1(), driehoekje.getOuder2())
                    .stream()
                    .filter(p -> !Objects.equals(persoon.getId(), p.getKind().getId()))
                    .map(huidigDriehoekje ->
                        BroerOfZusDto.builder()
                                .natuurlijkPersoon(modelMapper.map(huidigDriehoekje.getKind(), NatuurlijkPersoonDTO.class))
                                .isHalf(!huidigDriehoekje.heeftDezelfdeOudersAls(driehoekje))
                                .build())
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

package be.genealogie.applicatie;

import be.genealogie.domein.dto.AangetrouwdDto;
import be.genealogie.domein.dto.BroerOfZusDto;
import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.dto.NonkelsEnTantesDto;
import be.genealogie.domein.entiteit.GenealogischDriekhoekje;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.entiteit.Relatie;
import be.genealogie.domein.repository.GenealogischDriekhoekjeRepository;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import be.genealogie.domein.repository.RelatieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public List<NonkelsEnTantesDto> nonkelsEnTantes(NatuurlijkPersoonDTO persoon) {
        NatuurlijkPersoon persoonDb = persoonRepository.getById(persoon.getId());
        return genealogischDriekhoekjeRepository.findByKind(persoonDb)
                .map(driehoekje ->
                        Stream.of(broersEnZussenVan(modelMapper.map(driehoekje.getOuder1(), NatuurlijkPersoonDTO.class)),
                                        broersEnZussenVan(modelMapper.map(driehoekje.getOuder2(), NatuurlijkPersoonDTO.class)))
                                .flatMap(Collection::stream)
                                .map(broefOfZus -> NonkelsEnTantesDto.builder()
                                        .natuurlijkPersoon(broefOfZus.getNatuurlijkPersoon())
                                        .aangetrouwd(aangetrouwdMet(broefOfZus.getNatuurlijkPersoon()))
                                        .build())
                                .toList()
                ).orElseGet(Collections::emptyList);
    }

    private List<AangetrouwdDto> aangetrouwdMet(NatuurlijkPersoonDTO natuurlijkPersoon) {
        NatuurlijkPersoon persoon = persoonRepository.getById(natuurlijkPersoon.getId());
        return relatieRepository.zoekRelatiesMet(persoon)
                .stream()
                .map(relatie -> AangetrouwdDto.builder()
                        .natuurlijkPersoon(modelMapper.map(relatie.partnerVan(persoon), NatuurlijkPersoonDTO.class))
                        .actief(!Optional.ofNullable(relatie.getUitElkaar()).orElse(false))
                        .build())
                .toList();
    }
}

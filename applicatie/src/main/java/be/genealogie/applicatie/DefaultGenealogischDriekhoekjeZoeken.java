package be.genealogie.applicatie;

import be.genealogie.applicatie.mapper.NatuurlijkPersoonMapper;
import be.genealogie.domein.dto.*;
import be.genealogie.domein.entiteit.GenealogischDriekhoekje;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;
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
                .map(NatuurlijkPersoonMapper::map);
    }

    @Override
    public Optional<NatuurlijkPersoonDTO> vaderVan(NatuurlijkPersoonDTO persoon) {
        NatuurlijkPersoon persoonDb = persoonRepository.getById(persoon.getId());
        return genealogischDriekhoekjeRepository.findByKind(persoonDb)
                .map(GenealogischDriekhoekje::vader)
                .map(NatuurlijkPersoonMapper::map);
    }

    @Override
    public List<NatuurlijkPersoonDTO> kinderenVan(NatuurlijkPersoonDTO persoon) {
        NatuurlijkPersoon persoonDb = persoonRepository.getById(persoon.getId());
        return genealogischDriekhoekjeRepository.zoekOpOuder(persoonDb)
                .stream()
                .map(GenealogischDriekhoekje::getKind)
                .map(NatuurlijkPersoonMapper::map)
                .sorted(HeeftNatuurlijkPersoonDto.sorteerOpGeboortedatum())
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
                                .natuurlijkPersoon(NatuurlijkPersoonMapper.map(huidigDriehoekje.getKind()))
                                .isHalf(!huidigDriehoekje.heeftDezelfdeOudersAls(driehoekje))
                                .build())
                .sorted(HeeftNatuurlijkPersoonDto.sorteerOpGeboortedatum())
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
                        Stream.of(broersEnZussenVan(NatuurlijkPersoonMapper.map(driehoekje.getOuder1())),
                                        broersEnZussenVan(NatuurlijkPersoonMapper.map(driehoekje.getOuder2())))
                                .flatMap(Collection::stream)
                                .map(broefOfZus -> NonkelsEnTantesDto.builder()
                                        .natuurlijkPersoon(broefOfZus.natuurlijkPersoon())
                                        .aangetrouwd(aangetrouwdMet(broefOfZus.natuurlijkPersoon()))
                                        .build())
                                .sorted(HeeftNatuurlijkPersoonDto.sorteerOpGeboortedatum())
                                .toList()
                ).orElseGet(Collections::emptyList);
    }

    private List<AangetrouwdDto> aangetrouwdMet(NatuurlijkPersoonDTO natuurlijkPersoon) {
        NatuurlijkPersoon persoon = persoonRepository.getById(natuurlijkPersoon.getId());
        return relatieRepository.zoekRelatiesMet(persoon)
                .stream()
                .map(relatie -> AangetrouwdDto.builder()
                        .natuurlijkPersoon(NatuurlijkPersoonMapper.map(relatie.partnerVan(persoon)))
                        .actief(!Optional.ofNullable(relatie.getUitElkaar()).orElse(false))
                        .build())
                .sorted(HeeftNatuurlijkPersoonDto.sorteerOpGeboortedatum())
                .toList();
    }
}

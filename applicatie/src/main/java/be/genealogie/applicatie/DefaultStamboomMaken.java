package be.genealogie.applicatie;

import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.dto.StamboomDto;
import be.genealogie.domein.entiteit.GenealogischDriekhoekje;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.repository.GenealogischDriekhoekjeRepository;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class DefaultStamboomMaken implements StamboomMaken {
    private final GenealogischDriekhoekjeRepository genealogischDriekhoekjeRepository;
    private final NatuurlijkPersoonRepository natuurlijkPersoonRepository;

    @Override
    public StamboomDto nakomelingenVan(Long natuurlijkPersoonId) {
        NatuurlijkPersoon persoon = natuurlijkPersoonRepository.getById(natuurlijkPersoonId);
        return nakomelingenVan(persoon);
    }

    private StamboomDto nakomelingenVan(NatuurlijkPersoon persoon) {
        List<GenealogischDriekhoekje> kinderenVan = genealogischDriekhoekjeRepository.findKinderenVan(persoon);
        List<StamboomDto> kinderen = kinderenVan.stream().map(GenealogischDriekhoekje::getKind).map(this::nakomelingenVan).toList();

        return StamboomDto.builder()
                .naam(persoon.volledigeNaam())
                .kinderen(kinderen)
                .build();
    }

    @Override
    public StamboomDto vooroudersVan(Long natuurlijkPersoonId) {
        NatuurlijkPersoon persoon = natuurlijkPersoonRepository.getById(natuurlijkPersoonId);
        return vooroudersVan(persoon);
    }

    private StamboomDto vooroudersVan(NatuurlijkPersoon persoon) {
        Optional<GenealogischDriekhoekje> driehoekje = genealogischDriekhoekjeRepository.findByKind(persoon);
        List<StamboomDto> kinderen = driehoekje.map(d -> Stream.of(d.getMoeder(), d.getVader())
                .filter(Objects::nonNull)
                .map(this::vooroudersVan)
                .toList()).orElse(Collections.emptyList());


        return StamboomDto.builder()
                .naam(persoon.volledigeNaam())
                .kinderen(kinderen)
                .build();
    }
}

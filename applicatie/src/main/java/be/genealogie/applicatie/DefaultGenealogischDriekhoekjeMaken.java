package be.genealogie.applicatie;

import be.genealogie.domein.dto.*;
import be.genealogie.domein.entiteit.GenealogischDriekhoekje;
import be.genealogie.domein.entiteit.Huwelijk;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.repository.GenealogischDriekhoekjeRepository;
import be.genealogie.domein.repository.HuwelijkRepository;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultGenealogischDriekhoekjeMaken implements GenealogischDriekhoekjeMaken {
    private final NatuurlijkPersoonMaken natuurlijkPersoonMaken;
    private final NatuurlijkPersoonRepository natuurlijkPersoonRepository;
    private final GenealogischDriekhoekjeRepository genealogischDriekhoekjeRepository;
    private final HuwelijkRepository huwelijkRepository;
    private final ModelMapper modelMapper;

    @Override
    public GenealogischDriehoekjeDTO maakDriehoekje(GenealogischDriehoekjeDTO dto) {
        NatuurlijkPersoon kind = natuurlijkPersoonRepository.getById(dto.getKind().getId());
        NatuurlijkPersoon moeder = natuurlijkPersoonRepository.getById(dto.getMoeder().getId());
        NatuurlijkPersoon vader = natuurlijkPersoonRepository.getById(dto.getVader().getId());
        GenealogischDriekhoekje genealogischDriekhoekje = GenealogischDriekhoekje.builder().kind(kind).moeder(moeder).vader(vader).build();
        GenealogischDriekhoekje opgeslagenDriekhoekje = genealogischDriekhoekjeRepository.save(genealogischDriekhoekje);
        return modelMapper.map(opgeslagenDriekhoekje, GenealogischDriehoekjeDTO.class);
    }

    @Override
    public GenealogischDriehoekjeDTO registreerKindUitRelatie(KindUitRelatieDto kindUitRelatie) {
        Huwelijk huwelijk = huwelijkRepository.getById(kindUitRelatie.getHuwelijk().getId());
        NatuurlijkPersoonDTO kind = natuurlijkPersoonMaken.maak(kindUitRelatie.getKind());
        NatuurlijkPersoon kindUitDB = natuurlijkPersoonRepository.getById(kind.getId());

        GenealogischDriekhoekje genealogischDriekhoekje = GenealogischDriekhoekje.builder()
                .vader(huwelijk.getMan())
                .moeder(huwelijk.getVrouw())
                .kind(kindUitDB)
                .build();

        return modelMapper.map(genealogischDriekhoekje, GenealogischDriehoekjeDTO.class);
    }

    @Override
    public GenealogischDriehoekjeDTO registreerdOudersVanKind(OudersVanKindDto oudersVanKind) {
        NatuurlijkPersoonDTO moeder = natuurlijkPersoonMaken.maak(oudersVanKind.getMoeder());
        NatuurlijkPersoonDTO vader = natuurlijkPersoonMaken.maak(oudersVanKind.getVader());
        NatuurlijkPersoonDTO kind = oudersVanKind.getKind();

        natuurlijkPersoonMaken.maakHuwelijk(HuwelijkDto.builder()
                .persoon1(vader)
                .persoon2(moeder)
                .build());

        return maakDriehoekje(GenealogischDriehoekjeDTO.builder()
                .moeder(moeder)
                .vader(vader)
                .kind(kind)
                .build());

    }
}

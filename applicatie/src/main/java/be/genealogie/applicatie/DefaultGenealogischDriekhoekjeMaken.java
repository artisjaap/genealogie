package be.genealogie.applicatie;

import be.genealogie.domein.Geslacht;
import be.genealogie.domein.dto.*;
import be.genealogie.domein.entiteit.GenealogischDriekhoekje;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.entiteit.Relatie;
import be.genealogie.domein.repository.GenealogischDriekhoekjeRepository;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import be.genealogie.domein.repository.RelatieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultGenealogischDriekhoekjeMaken implements GenealogischDriekhoekjeMaken {
    private final NatuurlijkPersoonMaken natuurlijkPersoonMaken;
    private final NatuurlijkPersoonRepository natuurlijkPersoonRepository;
    private final GenealogischDriekhoekjeRepository genealogischDriekhoekjeRepository;
    private final RelatieRepository relatieRepository;
    private final ModelMapper modelMapper;

    @Override
    public GenealogischDriehoekjeDTO maakDriehoekje(GenealogischDriehoekjeDTO dto) {
        NatuurlijkPersoon kind = natuurlijkPersoonRepository.getById(dto.getKind().getId());
        NatuurlijkPersoon ouder1 = natuurlijkPersoonRepository.getById(dto.getOuder1().getId());
        NatuurlijkPersoon ouder2 = natuurlijkPersoonRepository.getById(dto.getOuder2().getId());
        GenealogischDriekhoekje genealogischDriekhoekje = GenealogischDriekhoekje.builder().kind(kind).ouder1(ouder1).ouder2(ouder2).build();
        GenealogischDriekhoekje opgeslagenDriekhoekje = genealogischDriekhoekjeRepository.save(genealogischDriekhoekje);
        return modelMapper.map(opgeslagenDriekhoekje, GenealogischDriehoekjeDTO.class);
    }

    @Override
    public GenealogischDriehoekjeDTO registreerKindUitRelatie(KindUitRelatieDto kindUitRelatie) {
        Relatie relatie = relatieRepository.getById(kindUitRelatie.getRelatie().getId());
        NatuurlijkPersoonDTO kind = natuurlijkPersoonMaken.maak(kindUitRelatie.getKind());
        NatuurlijkPersoon kindUitDB = natuurlijkPersoonRepository.getById(kind.getId());

        GenealogischDriekhoekje genealogischDriekhoekje = GenealogischDriekhoekje.builder()
                .ouder1(relatie.getPersoon1())
                .ouder2(relatie.getPersoon2())
                .kind(kindUitDB)
                .build();

        genealogischDriekhoekjeRepository.save(genealogischDriekhoekje);

        return modelMapper.map(genealogischDriekhoekje, GenealogischDriehoekjeDTO.class);
    }

    @Override
    public GenealogischDriehoekjeDTO registreerdOudersVanKind(OudersVanKindDto oudersVanKind) {
        NieuwNatuurlijkPersoonDTO moederDto = oudersVanKind.getMoeder();
        moederDto.setGeslacht(Geslacht.VROUW);
        NieuwNatuurlijkPersoonDTO vaderDto = oudersVanKind.getVader();
        vaderDto.setGeslacht(Geslacht.MAN);
        NatuurlijkPersoonDTO moeder = natuurlijkPersoonMaken.maak(moederDto);
        NatuurlijkPersoonDTO vader = natuurlijkPersoonMaken.maak(vaderDto);
        NatuurlijkPersoonDTO kind = oudersVanKind.getKind();

        natuurlijkPersoonMaken.maakHuwelijk(RelatieDto.builder()
                .persoon1(vader)
                .persoon2(moeder)
                .build());

        return maakDriehoekje(GenealogischDriehoekjeDTO.builder()
                .ouder1(moeder)
                .ouder2(vader)
                .kind(kind)
                .build());

    }
}

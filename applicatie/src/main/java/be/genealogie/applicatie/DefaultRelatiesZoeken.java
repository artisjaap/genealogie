package be.genealogie.applicatie;

import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.dto.RelatieDto;
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

@Component
@RequiredArgsConstructor
public class DefaultRelatiesZoeken implements ReleatiesZoeken{
    private final RelatieRepository relatieRepository;
    private final NatuurlijkPersoonRepository persoonRepository;
    private final GenealogischDriekhoekjeRepository genealogischDriekhoekjeRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<RelatieDto> relatiesMet(NatuurlijkPersoonDTO persoonDTO) {
        NatuurlijkPersoon persoon = persoonRepository.getById(persoonDTO.getId());

        List<Relatie> relaties = relatieRepository.findByPersoon1OrPersoon2(persoon, persoon);
        return relaties.stream()
                .map(relatie -> RelatieDto.builder()
                        .persoon1(modelMapper.map(relatie.getPersoon1(), NatuurlijkPersoonDTO.class))
                        .persoon2(modelMapper.map(relatie.getPersoon2(), NatuurlijkPersoonDTO.class))
                        .gehuwdOp(relatie.getGehuwedOp())
                        .gehuwdTe(relatie.getGehuwedTe())
                        .kinderen(genealogischDriekhoekjeRepository
                                .findByMoederAndVader(relatie.vrouw(), relatie.man())
                                .stream()
                                .map(GenealogischDriekhoekje::getKind)
                                .map(kind -> modelMapper.map(kind, NatuurlijkPersoonDTO.class))
                                .toList()
                        )
                        .build()).toList();
    }
}

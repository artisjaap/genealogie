package be.genealogie.applicatie;

import be.genealogie.domein.dto.GenealogischDriehoekjeDTO;
import be.genealogie.domein.entiteit.GenealogischDriekhoekje;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.repository.GenealogischDriekhoekjeRepository;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultGenealogischDriekhoekjeMaken implements GenealogischDriekhoekjeMaken {
    private final NatuurlijkPersoonRepository natuurlijkPersoonRepository;
    private final GenealogischDriekhoekjeRepository genealogischDriekhoekjeRepository;
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
}

package be.genealogie.applicatie;

import be.genealogie.domein.dto.RelatieUpdateDto;
import be.genealogie.domein.dto.RelatieDto;
import be.genealogie.domein.dto.ScheidingDto;
import be.genealogie.domein.entiteit.Relatie;
import be.genealogie.domein.repository.RelatieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultRelatieWijzigen implements RelatieWijzigen {
    private final RelatieRepository relatieRepository;
    private final ModelMapper modelMapper;

    @Override
    public RelatieDto wijzigRelatie(Long relatieId, RelatieUpdateDto huwelijk) {
        Relatie relatie = relatieRepository.getById(relatieId)
                .toBuilder()
                .gehuwedOp(huwelijk.huwelijkDatum())
                .gehuwedTe(huwelijk.gemeente())
                .uitElkaar(huwelijk.uitElkaar())
                .build();
        Relatie save = relatieRepository.save(relatie);
        return modelMapper.map(save, RelatieDto.class);
    }

    @Override
    public RelatieDto voegScheidingToe(Long relatieId, ScheidingDto scheiding) {
        Relatie relatie = relatieRepository.getById(relatieId)
                .toBuilder()
                .gescheidenOp(scheiding.datumScheiding())
                .build();
        Relatie save = relatieRepository.save(relatie);
        return modelMapper.map(save, RelatieDto.class);
    }
}

package be.genealogie.applicatie;

import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultNatuurlijkPersoonWijzigen implements NatuurlijkPersoonWijzigen {
    private final NatuurlijkPersoonRepository natuurlijkPersoonRepository;
    private final ModelMapper modelMapper;

    @Override
    public NatuurlijkPersoonDTO wijzig(NatuurlijkPersoonDTO persoon) {
        NatuurlijkPersoon gewijzigdePersoon = natuurlijkPersoonRepository.getById(persoon.getId())
                .toBuilder()
                .naam(persoon.getNaam())
                .voornaam(persoon.getVoornaam())
                .geborenOp(persoon.getGeborenOp())
                .geborenTe(persoon.getGeborenTe())
                .overledenOp(persoon.getOverledenOp())
                .overledenTe(persoon.getOverledenTe())
                .build();
        NatuurlijkPersoon save = natuurlijkPersoonRepository.save(gewijzigdePersoon);
        return modelMapper.map(save, NatuurlijkPersoonDTO.class);
    }
}

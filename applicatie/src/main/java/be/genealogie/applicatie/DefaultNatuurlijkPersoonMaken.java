package be.genealogie.applicatie;

import be.genealogie.applicatie.validatie.NatuurlijkPersoonValidator;
import be.genealogie.domein.dto.*;
import be.genealogie.domein.entiteit.Relatie;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;
import be.genealogie.domein.repository.RelatieRepository;
import be.genealogie.domein.repository.NatuurlijkPersoonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class DefaultNatuurlijkPersoonMaken implements NatuurlijkPersoonMaken {
    private final ModelMapper modelMapper;
    private final NatuurlijkPersoonRepository natuurlijkPersoonRepository;
    private final RelatieRepository relatieRepository;
    private final NatuurlijkPersoonValidator natuurlijkPersoonValidator;

    @Override
    public NatuurlijkPersoonDTO maak(NieuwNatuurlijkPersoonDTO natuurlijkPersoonDTO) {
        NatuurlijkPersoon natuurlijkPersoon = modelMapper.map(natuurlijkPersoonDTO, NatuurlijkPersoon.class);
        natuurlijkPersoonValidator.valideerVoorInsert(natuurlijkPersoon);
        NatuurlijkPersoon gewijzigdNatuurlijkPersoon = natuurlijkPersoonRepository.save(natuurlijkPersoon);
        return modelMapper.map(gewijzigdNatuurlijkPersoon, NatuurlijkPersoonDTO.class);
    }

    @Override
    public RelatieDto maakHuwelijk(RelatieDto huwelijk) {
        NatuurlijkPersoon man = natuurlijkPersoonRepository.getById(huwelijk.getMan().getId());
        NatuurlijkPersoon vrouw = natuurlijkPersoonRepository.getById(huwelijk.getVrouw().getId());
        return registreerHuwelijk(man, vrouw, huwelijk.getGehuwdOp(), huwelijk.getGehuwdTe());
    }

    @Override
    public RelatieDto maakHuwelijk(RelatieMetNieuwNatuurlijkPersoonDto huwelijk) {
        NatuurlijkPersoon bestaandNatuurlijkPersoon = natuurlijkPersoonRepository.getById(huwelijk.getPersoon1().getId());
        NatuurlijkPersoon nieuwNatuurlijkPersoon = modelMapper.map(huwelijk.getPersoon2(), NatuurlijkPersoon.class);
        natuurlijkPersoonRepository.save(nieuwNatuurlijkPersoon);
        return registreerHuwelijk(bestaandNatuurlijkPersoon, nieuwNatuurlijkPersoon, huwelijk.getGehuwdOp(), huwelijk.getGehuwdTe());
    }

    private RelatieDto registreerHuwelijk(NatuurlijkPersoon man, NatuurlijkPersoon vrouw, LocalDate gehuwdOp, String gehuwdTe) {
        Relatie relatieNieuw = Relatie.builder()
                .persoon1(man)
                .persoon2(vrouw)
                .gehuwedOp(gehuwdOp)
                .gehuwedTe(gehuwdTe)
                .build();

        Relatie opgeslagenRelatie = relatieRepository.save(relatieNieuw);
        return modelMapper.map(opgeslagenRelatie, RelatieDto.class);
    }

}

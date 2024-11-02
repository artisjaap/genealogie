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

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class DefaultNatuurlijkPersoonMaken implements NatuurlijkPersoonMaken {
    private final ModelMapper modelMapper;
    private final NatuurlijkPersoonRepository natuurlijkPersoonRepository;
    private final GenealogischDriekhoekjeRepository genealogischDriekhoekjeRepository;
    private final HuwelijkRepository huwelijkRepository;

    @Override
    public NatuurlijkPersoonDTO maak(NieuwNatuurlijkPersoonDTO natuurlijkPersoonDTO) {
        NatuurlijkPersoon natuurlijkPersoon = modelMapper.map(natuurlijkPersoonDTO, NatuurlijkPersoon.class);
        NatuurlijkPersoon gewijzigdNatuurlijkPersoon = natuurlijkPersoonRepository.save(natuurlijkPersoon);
        return modelMapper.map(gewijzigdNatuurlijkPersoon, NatuurlijkPersoonDTO.class);
    }

    @Override
    public HuwelijkDto maakHuwelijk(HuwelijkDto huwelijk) {
        NatuurlijkPersoon man = natuurlijkPersoonRepository.getById(huwelijk.getMan().getId());
        NatuurlijkPersoon vrouw = natuurlijkPersoonRepository.getById(huwelijk.getVrouw().getId());
        return registreerHuwelijk(man, vrouw, huwelijk.getGehuwdOp(), huwelijk.getGehuwdTe());
    }

    @Override
    public HuwelijkDto maakHuwelijk(HuwelijkMetNieuwNatuurlijkPersoonDto huwelijk) {
        NatuurlijkPersoon bestaandNatuurlijkPersoon = natuurlijkPersoonRepository.getById(huwelijk.getPersoon1().getId());
        NatuurlijkPersoon nieuwNatuurlijkPersoon = modelMapper.map(huwelijk.getPersoon2(), NatuurlijkPersoon.class);
        return registreerHuwelijk(bestaandNatuurlijkPersoon, nieuwNatuurlijkPersoon, huwelijk.getGehuwdOp(), huwelijk.getGehuwdTe());
    }

    private HuwelijkDto registreerHuwelijk(NatuurlijkPersoon persoon1, NatuurlijkPersoon persoon2, LocalDate gehuwdOp, String gehuwdTe) {
        Huwelijk huwelijkNieuw = Huwelijk.builder()
                .persoon1(persoon1)
                .persoon2(persoon2)
                .gehuwedOp(gehuwdOp)
                .gehuwedTe(gehuwdTe)
                .build();

        Huwelijk opgeslagenHuwelijk = huwelijkRepository.save(huwelijkNieuw);
        return modelMapper.map(opgeslagenHuwelijk, HuwelijkDto.class);
    }

}

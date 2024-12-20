package be.genealogie.applicatie.mapper;

import be.genealogie.applicatie.Sterrenbeeld;
import be.genealogie.domein.dto.NatuurlijkPersoonDTO;
import be.genealogie.domein.entiteit.NatuurlijkPersoon;

import java.util.List;
import java.util.Optional;

public class NatuurlijkPersoonMapper {

    public static List<NatuurlijkPersoonDTO> mapLijst(List<NatuurlijkPersoon> all) {
        return all.stream().map(NatuurlijkPersoonMapper::map).toList();
    }

    public static NatuurlijkPersoonDTO map(NatuurlijkPersoon natuurlijkPersoon) {
        return NatuurlijkPersoonDTO.builder()
                .id(natuurlijkPersoon.getId())
                .naam(natuurlijkPersoon.getNaam())
                .voornaam(natuurlijkPersoon.getVoornaam())
                .geborenOp(natuurlijkPersoon.getGeborenOp())
                .geborenTe(natuurlijkPersoon.getGeborenTe())
                .overledenOp(natuurlijkPersoon.getOverledenOp())
                .overledenTe(natuurlijkPersoon.getOverledenTe())
                .geslacht(natuurlijkPersoon.getGeslacht())
                .leeftijd(natuurlijkPersoon.leeftijd())
                .sterrenbeeld(Optional.ofNullable(natuurlijkPersoon.getGeborenOp()).map(Sterrenbeeld::voor).map(Sterrenbeeld::name).orElse(""))
                .build();
    }
}

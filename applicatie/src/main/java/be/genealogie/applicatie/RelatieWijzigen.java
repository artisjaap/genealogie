package be.genealogie.applicatie;

import be.genealogie.domein.dto.RelatieDto;
import be.genealogie.domein.dto.RelatieUpdateDto;
import be.genealogie.domein.dto.ScheidingDto;

public interface RelatieWijzigen {

    RelatieDto wijzigRelatie(Long relatieId, RelatieUpdateDto huwelijk);

    RelatieDto voegScheidingToe(Long relatieId, ScheidingDto scheiding);
}

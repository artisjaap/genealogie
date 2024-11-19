package be.genealogie.applicatie;

import be.genealogie.domein.dto.HuwelijkDto;
import be.genealogie.domein.dto.RelatieDto;
import be.genealogie.domein.dto.ScheidingDto;

public interface RelatieWijzigen {

    RelatieDto voegHuwelijkToe(Long relatieId, HuwelijkDto huwelijk);

    RelatieDto voegScheidingToe(Long relatieId, ScheidingDto scheiding);
}

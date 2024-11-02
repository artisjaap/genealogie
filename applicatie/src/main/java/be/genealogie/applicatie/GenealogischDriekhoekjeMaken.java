package be.genealogie.applicatie;

import be.genealogie.domein.dto.GenealogischDriehoekjeDTO;
import be.genealogie.domein.dto.KindUitRelatieDto;
import be.genealogie.domein.dto.OudersVanKindDto;

public interface GenealogischDriekhoekjeMaken {
    GenealogischDriehoekjeDTO maakDriehoekje(GenealogischDriehoekjeDTO dto);

    GenealogischDriehoekjeDTO registreerKindUitRelatie(KindUitRelatieDto kindUitRelatie);

    GenealogischDriehoekjeDTO registreerdOudersVanKind(OudersVanKindDto oudersVanKind);
}

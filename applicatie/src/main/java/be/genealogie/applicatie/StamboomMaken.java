package be.genealogie.applicatie;

import be.genealogie.domein.dto.StamboomDto;

public interface StamboomMaken {

    StamboomDto nakomelingenVan(Long natuurlijkPersoonId);

    StamboomDto vooroudersVan(Long natuurlijkPersoonId);
}

package be.genealogie.domein.dto;

import lombok.Builder;
import lombok.Data;

import javax.swing.text.StyledEditorKit;

@Data
@Builder
public class BroerOfZusDto implements HeeftNatuurlijkPersoonDto{
    private NatuurlijkPersoonDTO natuurlijkPersoon;
    @Builder.Default
    private Boolean isHalf = false;


    @Override
    public NatuurlijkPersoonDTO natuurlijkPersoon() {
        return natuurlijkPersoon;
    }
}

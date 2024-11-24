package be.genealogie.domein.dto;

import lombok.Builder;
import lombok.Data;

import javax.swing.text.StyledEditorKit;

@Data
@Builder
public class BroerOfZusDto {
    private NatuurlijkPersoonDTO natuurlijkPersoon;
    @Builder.Default
    private Boolean isHalf = false;
}

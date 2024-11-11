package be.genealogie.domein.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentHeaderDto {
    private Long id;
    private String type;
    private String fileName;
}

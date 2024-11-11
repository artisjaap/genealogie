package be.genealogie.domein.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentDto {
    private byte[] bytes;
    private String origineleFilename;
    private Long documentTypeId;
    private Long relatieId;
    private Long natuurlijkPersoonId;
}

package be.genealogie.domein.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentContentDto {
    private byte[] bytes;
    private MediaType mediaType;
}

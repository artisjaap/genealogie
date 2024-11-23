package be.genealogie.domein.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StamboomDto {
    //echart verwacht deze property namen,
    //TODO map naar echart tree dto
    String name;
    List<StamboomDto> children;
    List<String> value;
}

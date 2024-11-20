package be.genealogie.domein.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StamboomDto {
    String naam;
    List<StamboomDto> kinderen;
}

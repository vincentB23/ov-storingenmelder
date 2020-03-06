package be.vb.storingenmelder.dto.line;

import be.vb.storingenmelder.dto.line.LineDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lijnen"
})
public class LinesDto {
    @JsonProperty("lijnen")
    private List<LineDto> lineDtoList = null;

    public List<LineDto> getLineDtoList() {
        return lineDtoList;
    }
}

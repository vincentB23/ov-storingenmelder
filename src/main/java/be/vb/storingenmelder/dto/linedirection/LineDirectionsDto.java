package be.vb.storingenmelder.dto.linedirection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lijnrichtingen"
})
public class LineDirectionsDto {
    @JsonProperty("lijnrichtingen")
    private List<LineDirectionDto> lineDirectionDtoList = null;

    public List<LineDirectionDto> getLineDirectionDtoList() {
        return lineDirectionDtoList;
    }
}

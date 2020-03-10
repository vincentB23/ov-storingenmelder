package be.vb.storingenmelder.dto.disturbance.incoming;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "storingen"
})
public class DisturbancesDto {
    @JsonProperty("storingen")
    private List<DisturbanceDto> disturbanceDtoList = null;

    public List<DisturbanceDto> getDisturbanceDtoList() {
        return disturbanceDtoList;
    }
}

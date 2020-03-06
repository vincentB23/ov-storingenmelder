package be.vb.storingenmelder.dto.linedirection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("lijnrichtingen")
public class LineDirectionDto {
    @JsonProperty("richting")
    private String direction;
    @JsonProperty("omschrijving")
    private String description;
    @JsonProperty("bestemming")
    private String destination;

    public String getDirection() {
        return direction;
    }

    public String getDescription() {
        return description;
    }

    public String getDestination() {
        return destination;
    }
}

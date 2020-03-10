package be.vb.storingenmelder.dto.disturbance.incoming;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("storingen")
public class DisturbanceDto {
    @JsonProperty("titel")
    private String title;
    @JsonProperty("omschrijving")
    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

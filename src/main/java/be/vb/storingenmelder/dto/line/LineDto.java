package be.vb.storingenmelder.dto.line;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("lijnen")
public class LineDto {
    @JsonProperty("lijnnummer")
    private int number;
    @JsonProperty("lijnnummerPubliek")
    private String numberPublic;
    @JsonProperty("omschrijving")
    private String description;
    @JsonProperty("publiek")
    private boolean isPublic;
    @JsonProperty("vervoertype")
    private String transportType;
    @JsonProperty("bedieningtype")
    private String serviceType;

    public int getNumber() {
        return number;
    }

    public String getNumberPublic() {
        return numberPublic;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public String getTransportType() {
        return transportType;
    }

    public String getServiceType() {
        return serviceType;
    }
}

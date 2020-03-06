package be.vb.storingenmelder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("gemeenten")
public class CityDto {
    @JsonProperty("gemeentenummer")
    private int number;
    @JsonProperty("omschrijving")
    private String name;

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}

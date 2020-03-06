package be.vb.storingenmelder.dto.province;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("entiteiten")
public class ProvinceDto {
    @JsonProperty("entiteitnummer")
    private int number;
    @JsonProperty("entiteitcode")
    private String code;
    @JsonProperty("omschrijving")
    private String name;

    public int getNumber() {
        return number;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

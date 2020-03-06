package be.vb.storingenmelder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "gemeenten"
})
public class CitiesDto {
    @JsonProperty("gemeenten")
    private List<CityDto> cityDtoList = null;

    public List<CityDto> getCityDtoList() {
        return cityDtoList;
    }
}

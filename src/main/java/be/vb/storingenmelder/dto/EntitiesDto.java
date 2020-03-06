package be.vb.storingenmelder.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "entiteiten"
})
public class EntitiesDto {

    @JsonProperty("entiteiten")
    private List<ProvinceDto> provinceDtoList = null;

    public List<ProvinceDto> getProvinceDtoList() {
        return provinceDtoList;
    }
}
package be.vb.storingenmelder.dto.province;

import java.util.List;

import be.vb.storingenmelder.dto.province.ProvinceDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "entiteiten"
})
public class ProvincesDto {

    @JsonProperty("entiteiten")
    private List<ProvinceDto> provinceDtoList = null;

    public List<ProvinceDto> getProvinceDtoList() {
        return provinceDtoList;
    }
}
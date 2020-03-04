package be.vb.storingenmelder.dto;

import java.util.List;

import be.vb.storingenmelder.domain.Province;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "entiteiten"
})
public class EntiteitenDto {

    @JsonProperty("entiteiten")
    private List<Province> entiteiten = null;

    public List<Province> getEntiteiten() {
        return entiteiten;
    }
}
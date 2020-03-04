package be.vb.storingenmelder.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("entiteiten")
public class Province {
    @Id
    @JsonProperty("entiteitnummer")
    private int number;
    @JsonProperty("entiteitcode")
    private String code;
    @JsonProperty("omschrijving")
    private String name;
}

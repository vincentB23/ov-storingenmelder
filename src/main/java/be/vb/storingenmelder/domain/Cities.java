package be.vb.storingenmelder.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("gemeenten")
public class Cities {
    @Id
    @JsonProperty("gemeentenummer")
    private int number;
    @JsonProperty("omschrijving")
    private String name;
}

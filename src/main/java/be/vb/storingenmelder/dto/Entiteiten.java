package be.vb.storingenmelder.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "entiteitnummer",
        "entiteitcode",
        "omschrijving"
})
public class Entiteiten {

    /**
     * nummer entiteit (key waarde)
     * (Required)
     *
     */
    @JsonProperty("entiteitnummer")
    @JsonPropertyDescription("nummer entiteit (key waarde)")
    private String entiteitnummer;
    /**
     * code entiteit
     * (Required)
     *
     */
    @JsonProperty("entiteitcode")
    @JsonPropertyDescription("code entiteit")
    private String entiteitcode;
    /**
     * omschrijving entiteit
     * (Required)
     *
     */
    @JsonProperty("omschrijving")
    @JsonPropertyDescription("omschrijving entiteit")
    private String omschrijving;

    /**
     * nummer entiteit (key waarde)
     * (Required)
     *
     */
    @JsonProperty("entiteitnummer")
    public String getEntiteitnummer() {
        return entiteitnummer;
    }

    /**
     * nummer entiteit (key waarde)
     * (Required)
     *
     */
    @JsonProperty("entiteitnummer")
    public void setEntiteitnummer(String entiteitnummer) {
        this.entiteitnummer = entiteitnummer;
    }

    /**
     * code entiteit
     * (Required)
     *
     */
    @JsonProperty("entiteitcode")
    public String getEntiteitcode() {
        return entiteitcode;
    }

    /**
     * code entiteit
     * (Required)
     *
     */
    @JsonProperty("entiteitcode")
    public void setEntiteitcode(String entiteitcode) {
        this.entiteitcode = entiteitcode;
    }

    /**
     * omschrijving entiteit
     * (Required)
     *
     */
    @JsonProperty("omschrijving")
    public String getOmschrijving() {
        return omschrijving;
    }

    /**
     * omschrijving entiteit
     * (Required)
     *
     */
    @JsonProperty("omschrijving")
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
}

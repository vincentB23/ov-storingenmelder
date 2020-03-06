package be.vb.storingenmelder.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Province {
    @Id
    @Column(name = "ProvinceNumber")
    private int number;
    @Column(name = "ProvinceCode")
    private String code;
    @Column(name = "ProvinceName")
    private String name;
    @OneToMany(mappedBy = "province", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<City> cities;

    public Province() {
    }

    public Province(int number, String code, String name) {
        this.number = number;
        this.code = code;
        this.name = name;
    }
}

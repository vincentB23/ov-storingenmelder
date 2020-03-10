package be.vb.storingenmelder.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Entity
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProvinceId")
    private Long id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Province province = (Province) o;
        return Objects.equals(id, province.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package be.vb.storingenmelder.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CityId")
    private Long id;
    @Column(name = "CityNumber")
    private int number;
    @Column(name = "CityName")
    private String name;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ProvinceId", nullable = false)
    private Province province;

    public City() {
    }

    public City(int number, String name, Province province) {
        this.number = number;
        this.name = name;
        this.province = province;
    }

    public Province getProvince() {
        return province;
    }
}

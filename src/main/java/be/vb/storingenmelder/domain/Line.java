package be.vb.storingenmelder.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LineId")
    private Long id;
    @Column(name = "LineNumber")
    private int number;
    @Column(name = "LineNumberPublic")
    private String numberPublic;
    @Column(name = "LineDescription")
    private String description;
    @Column(name = "LineIsPublic")
    private boolean isPublic;
    @Column(name = "LineTransportType")
    private String transportType;
    @Column(name = "LineServiceType")
    private String serviceType;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ProvinceId", nullable = false)
    private Province province;

    public Line() {
    }

    public Line(int number, String numberPublic, String description, boolean isPublic, String transportType, String serviceType, Province province) {
        this.number = number;
        this.numberPublic = numberPublic;
        this.description = description;
        this.isPublic = isPublic;
        this.transportType = transportType;
        this.serviceType = serviceType;
        this.province = province;
    }

    public Long getId() {
        return id;
    }

    public Province getProvince() {
        return province;
    }

    public int getNumber() {
        return number;
    }
}

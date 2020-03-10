package be.vb.storingenmelder.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ProvinceId", nullable = false)
    private Province province;

    @OneToMany(mappedBy = "line", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<LineDirection> lineDirections;

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
}

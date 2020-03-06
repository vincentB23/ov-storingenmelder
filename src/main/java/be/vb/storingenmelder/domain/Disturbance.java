package be.vb.storingenmelder.domain;

import javax.persistence.*;

@Entity
public class Disturbance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DisturbanceId")
    private Long id;
    @Column(name = "DisturbanceTitle")
    private String title;
    @Column(name = "DisturbanceDescription")
    private String description;
    //TODO start and end date

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LineId", nullable = false)
    private Line line;
    @Column(name = "LineDirection")
    private String lineDirection;

    public Disturbance() {
    }

    public Disturbance(String title, String description, Line line, String lineDirection) {
        this.title = title;
        this.description = description;
        this.line = line;
        this.lineDirection = lineDirection;
    }
}

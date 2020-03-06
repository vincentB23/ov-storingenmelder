package be.vb.storingenmelder.domain;

import javax.persistence.*;

@Entity
public class LineDirection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LineDirectionId")
    private Long id;
    @Column(name = "LineDirection")
    private String direction;
    @Column(name = "LineDescription")
    private String description;
    @Column(name = "LineDestination")
    private String destination;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "LineId", nullable = false)
    private Line line;

    public LineDirection() {
    }

    public LineDirection(String direction, String description, String destination, Line line) {
        this.direction = direction;
        this.description = description;
        this.destination = destination;
        this.line = line;
    }

    public Line getLine() {
        return line;
    }

    public String getDirection() {
        return direction;
    }
}

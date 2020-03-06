package be.vb.storingenmelder.domain;

import javax.persistence.*;
import java.util.*;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LineId", nullable = false)
    private Line line;

    @ManyToMany(mappedBy = "lineDirections", fetch = FetchType.LAZY)
    Set<Tweet> tweets = new HashSet<>();

    public LineDirection() {
    }

    public LineDirection(String direction, String description, String destination, Line line) {
        this.direction = direction;
        this.description = description;
        this.destination = destination;
        this.line = line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineDirection that = (LineDirection) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Set<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(Set<Tweet> tweets) {
        this.tweets = tweets;
    }

    /*public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }
}

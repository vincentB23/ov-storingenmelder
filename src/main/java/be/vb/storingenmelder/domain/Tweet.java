package be.vb.storingenmelder.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Entity
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TweetId")
    private Long id;
    @Column(name = "TweetText", length = 1000)
    private String text;
    @Column(name = "TweetCreatedAt")
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Tweet_Linedirection",
            joinColumns = { @JoinColumn(name = "tweet_id")},
            inverseJoinColumns = { @JoinColumn(name = "linedirection_id") }
    )
    Set<LineDirection> lineDirections = new HashSet<>();

    public Tweet() {
    }

    public Tweet(String text, Date createdAt) {
        this.text = text;
        this.createdAt = createdAt.toInstant()
            .atZone(ZoneId.of("Europe/Brussels"))
            .toLocalDateTime();
    }

/*    public List<LineDirection> getLineDirections() {
        return lineDirections;
    }

    public void setLineDirections(List<LineDirection> lineDirections) {
        this.lineDirections = lineDirections;
    }*/

    public Set<LineDirection> getLineDirections() {
        return lineDirections;
    }

    public void setLineDirections(Set<LineDirection> lineDirections) {
        this.lineDirections = lineDirections;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return Objects.equals(id, tweet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

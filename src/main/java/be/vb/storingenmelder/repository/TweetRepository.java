package be.vb.storingenmelder.repository;

import be.vb.storingenmelder.domain.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    boolean existsTweetByCreatedAt(LocalDateTime createdAt);
}

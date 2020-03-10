package be.vb.storingenmelder.event;

import be.vb.storingenmelder.domain.Tweet;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class NewTweetEvent extends ApplicationEvent {
    private Tweet tweet;

    public NewTweetEvent(Object source, Tweet tweet) {
        super(source);
        this.tweet = tweet;
    }
}

package be.vb.storingenmelder.services.twitter;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import twitter4j.TwitterException;

@SpringBootTest
public class TwitterServiceTests {
    @Autowired
    private TwitterService twitterService;
    private static final Logger logger = LoggerFactory.getLogger(TwitterServiceTests.class);

    @Test
    void requestTweets() {
        try {
            twitterService.readDeLijnTwitterFeed();
        } catch (TwitterException e) {
            logger.warn("An error occured reading the twitter feed: " + e.getErrorMessage());
        }
    }
}

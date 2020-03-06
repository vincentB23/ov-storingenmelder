package be.vb.storingenmelder.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ovsm")
public class ConfigProperties {
    private String apiKey;
    private String consumerKey;
    private String consumerKeySecret;
    private String accessToken;
    private String accessTokenSecret;
    private int countTweets;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    public void setAccessTokenSecret(String accessTokenSecret) {
        this.accessTokenSecret = accessTokenSecret;
    }

    public int getCountTweets() {
        return countTweets;
    }

    public void setCountTweets(int countTweets) {
        this.countTweets = countTweets;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerKeySecret() {
        return consumerKeySecret;
    }

    public void setConsumerKeySecret(String consumerKeySecret) {
        this.consumerKeySecret = consumerKeySecret;
    }
}

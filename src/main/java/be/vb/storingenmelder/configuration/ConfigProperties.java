package be.vb.storingenmelder.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ovsm")
@Getter
@Setter
public class ConfigProperties {
    private String apiKey;
    private String consumerKey;
    private String consumerKeySecret;
    private String accessToken;
    private String accessTokenSecret;
    private int countTweets;
}

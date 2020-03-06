package be.vb.storingenmelder.services.twitter;

import be.vb.storingenmelder.configuration.ConfigProperties;
import be.vb.storingenmelder.domain.*;
import be.vb.storingenmelder.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TwitterService {
    private static final Logger logger = LoggerFactory.getLogger(TwitterService.class);
    private ProvinceRepository provinceRepository;
    private TweetRepository tweetRepository;
    private LineRepository lineRepository;
    private CityRepository cityRepository;
    private ConfigProperties configProperties;
    private Twitter twitter = null;

    public TwitterService(ProvinceRepository provinceRepository, TweetRepository tweetRepository, LineRepository lineRepository, CityRepository cityRepository, ConfigProperties configProperties) {
        this.provinceRepository = provinceRepository;
        this.tweetRepository = tweetRepository;
        this.lineRepository = lineRepository;
        this.cityRepository = cityRepository;
        this.configProperties = configProperties;

        try {
            twitter = initTwitter();
        } catch (TwitterException e) {
            logger.warn("An exception has occured trying to initialize Twitter: " + e.getErrorMessage());
            System.exit(1);
        }


    }

    private Twitter initTwitter() throws TwitterException {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setDebugEnabled(true);
        builder.setOAuthConsumerKey(configProperties.getConsumerKey());
        builder.setOAuthConsumerSecret(configProperties.getConsumerKeySecret());
        builder.setOAuthAccessToken(configProperties.getAccessToken());
        builder.setOAuthAccessTokenSecret(configProperties.getAccessTokenSecret());
        builder.setTweetModeExtended(true);
        Twitter twitter = new TwitterFactory(builder.build()).getInstance();
        return twitter;
    }

    public void readDeLijnTwitterFeed() throws TwitterException {
        Paging paging = new Paging();
        paging.setCount(50);

        ResponseList<Status> list = twitter.getUserTimeline("delijn", paging);
        list.forEach(
                status -> {
                    logger.info(status.toString());
                    List<String> hashtags = getHashtagsFromStatus(status);
                    if (hashtags.contains("verstoring")) {
                        hashtags.remove("delijn");
                        hashtags.remove("verstoring");
                        Province province = getProvinceFromStatus(hashtags);
                        List<LineDirection> lineDirections = getLineDirectionsFromStatus(status, province);

                        Tweet tweet = new Tweet(status.getText(), status.getCreatedAt());
                        lineDirections.forEach(
                                lineDirection -> {
                                    lineDirection.getTweets().add(tweet);
                                    tweet.getLineDirections().add(lineDirection);
                                }
                        );

                        tweetRepository.save(tweet);
                        logger.info("Imported tweet with text={}, lines={}", status.getText(), lineDirections.toString());
                    }
                }
        );
    }

    private List<LineDirection> getLineDirectionsFromStatus(Status status, Province province) {
        List<Line> lines = new ArrayList<>();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(status.getText());
        while (m.find()) {
            int lineNumber = Integer.parseInt(m.group());
            Line line = lineRepository.findByNumberAndProvince(lineNumber, province);
            if (line != null) {
                lines.add(line);
            }
        }

        List<LineDirection> lineDirections = new ArrayList<>();
        for (Line line : lines) {
            lineDirections.addAll(line.getLineDirections());
        }

        return lineDirections;
    }

    private Province getProvinceFromStatus(List<String> hashtags) {
        List<Province> provinces = provinceRepository.findAll();
        Province provinceWithHighestCount = null;
        int highestCount = 0;

        List<Province> provincesInStatus = new ArrayList<>();
        for (String hashtag : hashtags) {
            City city = cityRepository.findDistinctFirstByName(hashtag.toUpperCase());
            if (city != null) {
                provincesInStatus.add(city.getProvince());
            }
        }

        for (Province province : provinces) {
            int count = Collections.frequency(provincesInStatus, province);
            if (count > highestCount) {
                provinceWithHighestCount = province;
                highestCount = count;
            }
        }

        return provinceWithHighestCount;
    }

    private List<String> getHashtagsFromStatus(Status status) {
        List<String> hashtags = new ArrayList<>();
        final Pattern pattern = Pattern.compile("#\\w+");
        final Matcher matcher = pattern.matcher(status.getText());
        while (matcher.find()) {
            hashtags.add(matcher.group().substring(1).toLowerCase());
        }

        return hashtags;
    }
}

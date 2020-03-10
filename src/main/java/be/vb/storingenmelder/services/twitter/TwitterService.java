package be.vb.storingenmelder.services.twitter;

import be.vb.storingenmelder.configuration.ConfigProperties;
import be.vb.storingenmelder.domain.*;
import be.vb.storingenmelder.event.NewTweetEvent;
import be.vb.storingenmelder.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class TwitterService {
    private ProvinceRepository provinceRepository;
    private TweetRepository tweetRepository;
    private LineRepository lineRepository;
    private CityRepository cityRepository;
    private ConfigProperties configProperties;
    private Twitter twitter = null;
    private ApplicationEventPublisher applicationEventPublisher;

    public TwitterService(ProvinceRepository provinceRepository, TweetRepository tweetRepository, LineRepository lineRepository, CityRepository cityRepository, ConfigProperties configProperties, ApplicationEventPublisher applicationEventPublisher) {
        this.provinceRepository = provinceRepository;
        this.tweetRepository = tweetRepository;
        this.lineRepository = lineRepository;
        this.cityRepository = cityRepository;
        this.configProperties = configProperties;
        this.applicationEventPublisher = applicationEventPublisher;

        try {
            twitter = initTwitter();
        } catch (TwitterException e) {
            log.warn("An exception has occured trying to initialize Twitter: " + e.getErrorMessage());
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

    @Scheduled(fixedRate = 120000, initialDelayString = "${fixed-delay.in.milliseconds}")
    public void readDeLijnTwitterFeed() throws TwitterException {
        log.info("Started reading the twitter feed.");

        Paging paging = new Paging();
        paging.setCount(50);

        ResponseList<Status> list = twitter.getUserTimeline("delijn", paging);
        list.forEach(
                status -> {
                    LocalDateTime createdAt = convertDate(status.getCreatedAt());
                    List<String> hashtags = getHashtagsFromStatus(status);

                    if (hashtags.contains("verstoring") && (!tweetRepository.existsTweetByCreatedAt(createdAt))) {
                        hashtags.remove("delijn");
                        hashtags.remove("verstoring");
                        Province province = getProvinceFromStatus(hashtags);
                        List<LineDirection> lineDirections = getLineDirectionsFromStatus(status, province);

                        Tweet tweet = new Tweet(status.getText(), createdAt);
                        lineDirections.forEach(
                                lineDirection -> {
                                    lineDirection.getTweets().add(tweet);
                                    tweet.getLineDirections().add(lineDirection);
                                }
                        );

                        Tweet newTweet = tweetRepository.save(tweet);
                        publishEvent(newTweet);
                        log.info("Imported tweet with id={}", newTweet.getId());
                    }
                }
        );

        log.info("Finished reading the twitter feed.");
    }

    private LocalDateTime convertDate(Date createdAt) {
        return createdAt.toInstant()
                .atZone(ZoneId.of("Europe/Brussels"))
                .toLocalDateTime();
    }

    private List<LineDirection> getLineDirectionsFromStatus(Status status, Province province) {
        List<Line> lines = new ArrayList<>();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(status.getText());
        while (m.find()) {
            String number = m.group();
            int lineNumber = Integer.parseInt(number);
            Line line = lineRepository.findByNumberAndProvince(lineNumber, province);
            if (line != null) {
                lines.add(line);
            } else {
                line = lineRepository.findByNumberPublicAndProvince(number, province);
                if (line != null) {
                    lines.add(line);
                }
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
            log.debug("hashtag: " + hashtag);
            City city = cityRepository.findDistinctFirstByNameContaining(hashtag.toUpperCase());
            if (city != null) {
                log.info("city: " + city.getName());
                provincesInStatus.add(city.getProvince());
            }
        }

        provincesInStatus.forEach(province -> log.debug("province in status: " + province.getName()));
        for (Province province : provinces) {
            int count = Collections.frequency(provincesInStatus, province);
            if (count > highestCount) {
                provinceWithHighestCount = province;
                highestCount = count;
            }
        }

        log.info("province with highest count: " + provinceWithHighestCount.getName());
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

    private void publishEvent(Tweet tweet) {
        NewTweetEvent newTweetEvent = new NewTweetEvent(this, tweet);
        applicationEventPublisher.publishEvent(newTweetEvent);
    }
}

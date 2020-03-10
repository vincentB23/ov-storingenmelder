package be.vb.storingenmelder.services;

import be.vb.storingenmelder.domain.Disturbance;
import be.vb.storingenmelder.domain.Line;
import be.vb.storingenmelder.domain.LineDirection;
import be.vb.storingenmelder.domain.Tweet;
import be.vb.storingenmelder.dto.disturbance.incoming.DisturbanceDto;
import be.vb.storingenmelder.dto.disturbance.incoming.DisturbancesDto;
import be.vb.storingenmelder.event.NewTweetEvent;
import be.vb.storingenmelder.repository.DisturbanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
public class DisturbanceService {
    private final String BASE_URL = "https://api.delijn.be/DLKernOpenData/api/v1/lijnen/{entiteitnummer}/{lijnnummer}/lijnrichtingen/{richting}/storingen";

    private DisturbanceRepository disturbanceRepository;
    private RestTemplate restTemplate;

    public DisturbanceService(DisturbanceRepository disturbanceRepository, RestTemplate restTemplate) {
        this.disturbanceRepository = disturbanceRepository;
        this.restTemplate = restTemplate;
    }

    @Async
    @EventListener
    public void handleNewTweetEvent(NewTweetEvent event) {
        Tweet tweet = event.getTweet();
        log.info("New tweet event received: " + tweet.getId());
        importDisturbancesFromTweet(tweet);
    }

    public void importDisturbance(long provinceNumber, Line line, String direction) {
        ResponseEntity<DisturbancesDto> response = restTemplate.getForEntity(BASE_URL, DisturbancesDto.class, provinceNumber, line.getNumber(), direction);
        List<DisturbanceDto> disturbanceDtoList = response.getBody().getDisturbanceDtoList();
        disturbanceDtoList.forEach(
                disturbanceDto -> {
                    Disturbance disturbance = new Disturbance(disturbanceDto.getTitle(), disturbanceDto.getDescription(), line, direction);
                    disturbanceRepository.save(disturbance);
                    log.info("Imported disturbance with title={}, lineNr={}, lineProvince={}", disturbanceDto.getTitle(), line.getNumber(), line.getProvince().getName());
                }
        );
    }

    public List<Disturbance> getAllDisturbances() {
        return disturbanceRepository.findAll();
    }

    private void importDisturbancesFromTweet(Tweet tweet) {
        for (LineDirection lineDirection : tweet.getLineDirections()) {
            Line line = lineDirection.getLine();
            importDisturbance(line.getProvince().getNumber(), line, lineDirection.getDirection());
        }
    }
}

package be.vb.storingenmelder.services.importservices;

import be.vb.storingenmelder.domain.Line;
import be.vb.storingenmelder.domain.LineDirection;
import be.vb.storingenmelder.dto.linedirection.LineDirectionDto;
import be.vb.storingenmelder.dto.linedirection.LineDirectionsDto;
import be.vb.storingenmelder.repository.LineDirectionRepository;
import be.vb.storingenmelder.repository.LineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LineDirectionsImportService {
    private static final Logger logger = LoggerFactory.getLogger(LineDirectionsImportService.class);
    private final String BASE_URL = "https://api.delijn.be/DLKernOpenData/api/v1/lijnen/{entiteitnummer}/{lijnnummer}/lijnrichtingen";

    private final LineRepository lineRepository;
    private final LineDirectionRepository lineDirectionRepository;
    private final RestTemplate restTemplate;

    public LineDirectionsImportService(LineRepository lineRepository, LineDirectionRepository lineDirectionRepository, RestTemplate restTemplate) {
        this.lineRepository = lineRepository;
        this.lineDirectionRepository = lineDirectionRepository;
        this.restTemplate = restTemplate;
    }

    public void importLineDirections(int limit) {
        logger.info("Importing lineDirections has started.");

        List<Line> lines = lineRepository.findAll();
        if (limit == -1) {
            limit=lines.size()-1;
        }
        lines.subList(0,limit).forEach(
                line -> {
                    ResponseEntity<LineDirectionsDto> response = restTemplate.getForEntity(BASE_URL, LineDirectionsDto.class, line.getProvince().getNumber(), line.getNumber());
                    List<LineDirectionDto> lineDirectionDtoList = response.getBody().getLineDirectionDtoList();
                    lineDirectionDtoList.forEach(
                            lineDirectionDto -> {
                                LineDirection lineDirection = new LineDirection(
                                        lineDirectionDto.getDirection(),
                                        lineDirectionDto.getDescription(),
                                        lineDirectionDto.getDestination(),
                                        line
                                );
                                lineDirectionRepository.save(lineDirection);
                                logger.info("Imported lineDirection with direction={}, description={}, destination={}, linenumber={}", lineDirectionDto.getDirection(), lineDirectionDto.getDescription(), lineDirectionDto.getDestination(), lineDirection.getLine().getNumber());
                            }
                    );
                }
        );

        logger.info("Importing lineDirections has finished.");
    }
}

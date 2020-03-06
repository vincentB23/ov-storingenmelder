package be.vb.storingenmelder.services.importservices;

import be.vb.storingenmelder.domain.Disturbance;
import be.vb.storingenmelder.domain.Line;
import be.vb.storingenmelder.domain.LineDirection;
import be.vb.storingenmelder.dto.disturbance.DisturbanceDto;
import be.vb.storingenmelder.dto.disturbance.DisturbancesDto;
import be.vb.storingenmelder.repository.DisturbanceRepository;
import be.vb.storingenmelder.repository.LineDirectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DisturbancesImportService {
    private static final Logger logger = LoggerFactory.getLogger(DisturbancesImportService.class);
    private final String BASE_URL = "https://api.delijn.be/DLKernOpenData/api/v1/lijnen/{entiteitnummer}/{lijnnummer}/lijnrichtingen/{richting}/storingen";

    private final LineDirectionRepository lineDirectionRepository;
    private final DisturbanceRepository disturbanceRepository;
    private final RestTemplate restTemplate;

    public DisturbancesImportService(LineDirectionRepository lineDirectionRepository, DisturbanceRepository disturbanceRepository, RestTemplate restTemplate) {
        this.lineDirectionRepository = lineDirectionRepository;
        this.disturbanceRepository = disturbanceRepository;
        this.restTemplate = restTemplate;
    }

    public void importAllDisturbances() {
        logger.info("Importing disturbances has started.");

        List<LineDirection> lineDirections = lineDirectionRepository.findAll();

        lineDirections.forEach(
                lineDirection -> {
                    importDisturbance(lineDirection.getLine().getProvince().getNumber(), lineDirection.getLine(), lineDirection.getDirection());
                }
        );

        logger.info("Importing disturbances has finished.");
    }

    private void importDisturbance(long provinceNumber, Line line, String direction) {
        ResponseEntity<DisturbancesDto> response = restTemplate.getForEntity(BASE_URL, DisturbancesDto.class, provinceNumber, line.getNumber(), direction);
        List<DisturbanceDto> disturbanceDtoList = response.getBody().getDisturbanceDtoList();
        disturbanceDtoList.forEach(
                disturbanceDto -> {
                    Disturbance disturbance = new Disturbance(disturbanceDto.getTitle(), disturbanceDto.getDescription(), line, direction);
                    disturbanceRepository.save(disturbance);
                }
        );
    }
}

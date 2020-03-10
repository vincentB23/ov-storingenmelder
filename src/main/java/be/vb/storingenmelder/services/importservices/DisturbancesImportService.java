package be.vb.storingenmelder.services.importservices;

import be.vb.storingenmelder.domain.LineDirection;
import be.vb.storingenmelder.repository.DisturbanceRepository;
import be.vb.storingenmelder.repository.LineDirectionRepository;
import be.vb.storingenmelder.services.DisturbanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DisturbancesImportService {
    private static final Logger logger = LoggerFactory.getLogger(DisturbancesImportService.class);

    private final LineDirectionRepository lineDirectionRepository;
    private final DisturbanceRepository disturbanceRepository;
    private final RestTemplate restTemplate;
    private final DisturbanceService disturbanceService;

    public DisturbancesImportService(LineDirectionRepository lineDirectionRepository, DisturbanceRepository disturbanceRepository, RestTemplate restTemplate, DisturbanceService disturbanceService) {
        this.lineDirectionRepository = lineDirectionRepository;
        this.disturbanceRepository = disturbanceRepository;
        this.restTemplate = restTemplate;
        this.disturbanceService = disturbanceService;
    }

    public void importAllDisturbances() {
        logger.info("Importing disturbances has started.");

        List<LineDirection> lineDirections = lineDirectionRepository.findAll();

        lineDirections.forEach(
                lineDirection -> {
                    disturbanceService.importDisturbance(lineDirection.getLine().getProvince().getNumber(), lineDirection.getLine(), lineDirection.getDirection());
                }
        );

        logger.info("Importing disturbances has finished.");
    }
}

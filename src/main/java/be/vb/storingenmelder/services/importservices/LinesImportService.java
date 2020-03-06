package be.vb.storingenmelder.services.importservices;

import be.vb.storingenmelder.domain.Line;
import be.vb.storingenmelder.domain.Province;
import be.vb.storingenmelder.dto.line.LineDto;
import be.vb.storingenmelder.dto.line.LinesDto;
import be.vb.storingenmelder.repository.LineRepository;
import be.vb.storingenmelder.repository.ProvinceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LinesImportService {
    private static final Logger logger = LoggerFactory.getLogger(LinesImportService.class);
    private final String BASE_URL = "https://api.delijn.be/DLKernOpenData/api/v1/entiteiten/{entiteitnummer}/lijnen";

    private final ProvinceRepository provinceRepository;
    private final LineRepository lineRepository;
    private final RestTemplate restTemplate;

    public LinesImportService(ProvinceRepository provinceRepository, LineRepository lineRepository, RestTemplate restTemplate) {
        this.provinceRepository = provinceRepository;
        this.lineRepository = lineRepository;
        this.restTemplate = restTemplate;
    }

    public void importLines() {
        logger.info("Importing lines has started.");

        for (int i = 1; i <= 5; i++) {
            Province province = provinceRepository.findById((long) i).orElseThrow();
            ResponseEntity<LinesDto> response = restTemplate.getForEntity(BASE_URL, LinesDto.class, i);
            List<LineDto> lineDtoList = response.getBody().getLineDtoList();
            lineDtoList.forEach(
                    lineDto -> {
                        Line line = new Line(
                                lineDto.getNumber(),
                                lineDto.getNumberPublic(),
                                lineDto.getDescription(),
                                lineDto.isPublic(),
                                lineDto.getTransportType(),
                                lineDto.getServiceType(),
                                province
                        );
                        lineRepository.save(line);
                    }
            );

        }

        logger.info("Importing lines has finished.");
    }
}

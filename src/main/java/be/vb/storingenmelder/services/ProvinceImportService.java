package be.vb.storingenmelder.services;

import be.vb.storingenmelder.domain.Province;
import be.vb.storingenmelder.dto.EntiteitenDto;
import be.vb.storingenmelder.repository.ProvinceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class ProvinceImportService {
    private static final Logger logger = LoggerFactory.getLogger(ProvinceImportService.class);
    private final String BASE_URL = "https://api.delijn.be/DLKernOpenData/api/v1/entiteiten";

    private final ProvinceRepository repository;
    private final RestTemplate restTemplate;

    public ProvinceImportService(ProvinceRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public void importEntities() {
        logger.info("Importing entities has started.");

        ResponseEntity<EntiteitenDto> entiteitenDto = restTemplate.getForEntity(BASE_URL, EntiteitenDto.class);
        List<Province> provinces = entiteitenDto.getBody().getEntiteiten();
        provinces.forEach(
                repository::save
        );
        logger.info("Importing entities has finished.");
    }
}

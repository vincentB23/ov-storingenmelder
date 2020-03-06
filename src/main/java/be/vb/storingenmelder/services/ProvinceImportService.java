package be.vb.storingenmelder.services;

import be.vb.storingenmelder.domain.Province;
import be.vb.storingenmelder.dto.EntitiesDto;
import be.vb.storingenmelder.dto.ProvinceDto;
import be.vb.storingenmelder.repository.ProvinceRepository;
import lombok.extern.slf4j.Slf4j;
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
        logger.info("Importing provinces has started.");

        ResponseEntity<EntitiesDto> entiteitenDto = restTemplate.getForEntity(BASE_URL, EntitiesDto.class);
        List<ProvinceDto> provinceDtoList = entiteitenDto.getBody().getProvinceDtoList();
        provinceDtoList.forEach(
                provinceDto -> {
                    Province province = new Province(provinceDto.getNumber(), provinceDto.getCode(), provinceDto.getName());
                    repository.save(province);
                }
        );
        logger.info("Importing provinces has finished.");
    }
}

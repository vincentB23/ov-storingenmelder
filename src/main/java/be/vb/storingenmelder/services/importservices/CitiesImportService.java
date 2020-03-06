package be.vb.storingenmelder.services.importservices;

import be.vb.storingenmelder.domain.City;
import be.vb.storingenmelder.domain.Province;
import be.vb.storingenmelder.dto.city.CitiesDto;
import be.vb.storingenmelder.dto.city.CityDto;
import be.vb.storingenmelder.repository.CityRepository;
import be.vb.storingenmelder.repository.ProvinceRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class CitiesImportService {
    private static final Logger logger = LoggerFactory.getLogger(CitiesImportService.class);
    private final String BASE_URL = "https://api.delijn.be/DLKernOpenData/api/v1/entiteiten/{entiteitnummer}/gemeenten";

    private final ProvinceRepository provinceRepository;
    private final CityRepository cityRepository;
    private final RestTemplate restTemplate;

    public CitiesImportService(ProvinceRepository provinceRepository, CityRepository cityRepository, RestTemplate restTemplate) {
        this.provinceRepository = provinceRepository;
        this.cityRepository = cityRepository;
        this.restTemplate = restTemplate;
    }

    public void importCities() {
        logger.info("Importing cities has started.");

        for (int i = 1; i <= 5; i++) {
            Province province = provinceRepository.findById((long) i).orElseThrow();
            ResponseEntity<CitiesDto> response = restTemplate.getForEntity(BASE_URL, CitiesDto.class, i);
            List<CityDto> cityDtoList = response.getBody().getCityDtoList();
            cityDtoList.forEach(
                    cityDto -> {
                        City city = new City(cityDto.getNumber(), cityDto.getName(), province);
                        cityRepository.save(city);
                    }
            );
        }

        logger.info("Importing cities has finished.");
    }
}

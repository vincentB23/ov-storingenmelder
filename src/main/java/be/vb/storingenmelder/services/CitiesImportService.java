package be.vb.storingenmelder.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CitiesImportService {
    private final String BASE_URL = "https://api.delijn.be/DLKernOpenData/api/v1/entiteiten";
}

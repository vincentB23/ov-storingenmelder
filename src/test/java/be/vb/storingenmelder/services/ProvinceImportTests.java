package be.vb.storingenmelder.services;

import be.vb.storingenmelder.services.importservices.ProvinceImportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProvinceImportTests {
    @Autowired
    private ProvinceImportService provinceImportService;

    @Test
    void importEntities() {
        provinceImportService.importEntities();
    }
}

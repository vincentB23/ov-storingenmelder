package be.vb.storingenmelder;

import be.vb.storingenmelder.services.CitiesImportService;
import be.vb.storingenmelder.services.LinesImportService;
import be.vb.storingenmelder.services.ProvinceImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class StoringenmelderApplication implements CommandLineRunner {
	@Autowired
	private ProvinceImportService provinceImportService;
	@Autowired
	private CitiesImportService citiesImportService;
	@Autowired
	private LinesImportService linesImportService;

	public static void main(String[] args) {
		SpringApplication.run(StoringenmelderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		provinceImportService.importEntities();
		citiesImportService.importCities();
		linesImportService.importLines();
	}
}

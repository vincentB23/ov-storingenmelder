package be.vb.storingenmelder.repository;

import be.vb.storingenmelder.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    City findDistinctFirstByName(String name);
    City findDistinctFirstByNameContaining(String name);
}

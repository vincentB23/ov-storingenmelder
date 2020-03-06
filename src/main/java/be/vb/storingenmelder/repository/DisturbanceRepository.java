package be.vb.storingenmelder.repository;

import be.vb.storingenmelder.domain.Disturbance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisturbanceRepository extends JpaRepository<Disturbance, Long> {
}

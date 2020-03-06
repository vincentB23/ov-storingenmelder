package be.vb.storingenmelder.repository;

import be.vb.storingenmelder.domain.LineDirection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineDirectionRepository extends JpaRepository<LineDirection, Long> {
}

package be.vb.storingenmelder.repository;

import be.vb.storingenmelder.domain.Line;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepository extends JpaRepository<Line, Long> {
}

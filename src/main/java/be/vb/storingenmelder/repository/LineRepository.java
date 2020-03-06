package be.vb.storingenmelder.repository;

import be.vb.storingenmelder.domain.Line;
import be.vb.storingenmelder.domain.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepository extends JpaRepository<Line, Long> {
    Line findByNumberAndProvince(int number, Province province);
}

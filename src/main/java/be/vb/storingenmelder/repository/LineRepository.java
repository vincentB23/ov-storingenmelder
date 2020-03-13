package be.vb.storingenmelder.repository;

import be.vb.storingenmelder.domain.Line;
import be.vb.storingenmelder.domain.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepository extends JpaRepository<Line, Long> {
    Line findFirstByNumberAndProvince(int number, Province province);
    Line findFirstByNumberPublicAndProvince(String number, Province province);
}

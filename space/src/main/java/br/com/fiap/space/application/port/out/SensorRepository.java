package br.com.fiap.space.application.port.out;

import br.com.fiap.space.application.core.domain.model.Sensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Page<Sensor> findAllByAtivoTrue(Pageable pageable);
    Page<Sensor> findAllByEstufaIdAndAtivoTrue(Long estufaId, Pageable pageable);
}

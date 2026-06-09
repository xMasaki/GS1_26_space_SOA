package br.com.fiap.space.application.port.out;

import br.com.fiap.space.application.core.domain.model.LeituraSensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface LeituraSensorRepository extends JpaRepository<LeituraSensor, Long> {
    Page<LeituraSensor> findAllBySensorId(Long sensorId, Pageable pageable);
    Page<LeituraSensor> findAllByEstufaId(Long estufaId, Pageable pageable);
    Page<LeituraSensor> findAllBySensorIdAndDataRegistroBetween(Long sensorId, LocalDateTime inicio, LocalDateTime fim, Pageable pageable);
    Page<LeituraSensor> findAllByEstufaIdAndDataRegistroBetween(Long estufaId, LocalDateTime inicio, LocalDateTime fim, Pageable pageable);
}

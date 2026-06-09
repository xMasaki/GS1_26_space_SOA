package br.com.fiap.space.application.port.out;

import br.com.fiap.space.application.core.domain.model.Estufa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstufaRepository extends JpaRepository<Estufa, Long> {
    Page<Estufa> findAllByAtivoTrue(Pageable pageable);
    boolean existsByCodigo(String codigo);
}

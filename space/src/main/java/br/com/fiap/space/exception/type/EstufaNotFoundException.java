package br.com.fiap.space.exception.type;

import jakarta.persistence.EntityNotFoundException;

public class EstufaNotFoundException extends EntityNotFoundException {
    public EstufaNotFoundException(Long id) {
        super("Estufa não encontrada com id: " + id);
    }
}

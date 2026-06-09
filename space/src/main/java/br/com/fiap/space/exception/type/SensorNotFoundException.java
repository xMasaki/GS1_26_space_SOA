package br.com.fiap.space.exception.type;

import jakarta.persistence.EntityNotFoundException;

public class SensorNotFoundException extends EntityNotFoundException {
    public SensorNotFoundException(Long id) {
        super("Sensor não encontrado com id: " + id);
    }
}

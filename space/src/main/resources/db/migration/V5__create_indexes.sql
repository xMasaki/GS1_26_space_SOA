-- Estufas: busca por ativo (listagem paginada)
CREATE INDEX idx_estufas_ativo      ON estufas       (ativo);
CREATE INDEX idx_estufas_usuario_id ON estufas       (usuario_id);

-- Sensores: filtros frequentes
CREATE INDEX idx_sensores_ativo      ON sensores     (ativo);
CREATE INDEX idx_sensores_estufa_id  ON sensores     (estufa_id);
CREATE INDEX idx_sensores_usuario_id ON sensores     (usuario_id);

-- Leituras: queries de histórico e relatório por sensor/estufa + data
CREATE INDEX idx_leituras_sensor_id            ON leituras_sensor (sensor_id);
CREATE INDEX idx_leituras_estufa_id            ON leituras_sensor (estufa_id);
CREATE INDEX idx_leituras_data_registro        ON leituras_sensor (data_registro);
CREATE INDEX idx_leituras_nivel_alerta         ON leituras_sensor (nivel_alerta);
CREATE INDEX idx_leituras_sensor_data          ON leituras_sensor (sensor_id, data_registro);
CREATE INDEX idx_leituras_estufa_data          ON leituras_sensor (estufa_id, data_registro);

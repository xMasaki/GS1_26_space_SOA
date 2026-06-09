CREATE TABLE leituras_sensor (
    id             BIGINT        NOT NULL AUTO_INCREMENT,
    sensor_id      BIGINT        NOT NULL,
    estufa_id      BIGINT        NOT NULL,
    data_registro  DATETIME(6)   NOT NULL,
    valor          DOUBLE        NOT NULL,
    unidade        VARCHAR(20),
    nivel_alerta   ENUM('INFO','AVISO','CRITICO','EMERGENCIA') NOT NULL,
    alertas        VARCHAR(1000),
    usuario_id     BIGINT        NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_leituras_sensor  FOREIGN KEY (sensor_id)  REFERENCES sensores  (id),
    CONSTRAINT fk_leituras_estufa  FOREIGN KEY (estufa_id)  REFERENCES estufas   (id),
    CONSTRAINT fk_leituras_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios  (id)
);

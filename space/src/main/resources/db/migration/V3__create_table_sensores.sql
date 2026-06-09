CREATE TABLE sensores (
    id                      BIGINT       NOT NULL AUTO_INCREMENT,
    ativo                   TINYINT(1)   NOT NULL DEFAULT 1,
    nome                    VARCHAR(150) NOT NULL,
    identificador           VARCHAR(50)  NOT NULL,
    tipo                    ENUM('TEMPERATURA','UMIDADE','CO2','O2','RADIACAO','PH_AGUA','LUMINOSIDADE') NOT NULL,
    localizacao             VARCHAR(255),
    estufa_id               BIGINT       NOT NULL,
    limite_inferior_aviso   DOUBLE,
    limite_superior_aviso   DOUBLE,
    limite_inferior_critico DOUBLE,
    limite_superior_critico DOUBLE,
    ultima_leitura          DATETIME(6),
    usuario_id              BIGINT       NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT uq_sensores_identificador UNIQUE (identificador),
    CONSTRAINT fk_sensores_estufa  FOREIGN KEY (estufa_id)  REFERENCES estufas   (id),
    CONSTRAINT fk_sensores_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios  (id)
);

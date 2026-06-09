CREATE TABLE estufas (
    id               BIGINT        NOT NULL AUTO_INCREMENT,
    ativo            TINYINT(1)    NOT NULL DEFAULT 1,
    nome             VARCHAR(150)  NOT NULL,
    codigo           VARCHAR(50)   NOT NULL,
    localizacao      VARCHAR(255)  NOT NULL,
    descricao        VARCHAR(500),
    latitude_marte   DOUBLE,
    longitude_marte  DOUBLE,
    usuario_id       BIGINT        NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT uq_estufas_codigo UNIQUE (codigo),
    CONSTRAINT fk_estufas_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);

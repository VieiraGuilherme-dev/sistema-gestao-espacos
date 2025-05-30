CREATE TABLE IF NOT EXISTS usuario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL -- ADMIN, GESTOR, PROFESSOR, ALUNO, FUNCIONARIO
);

CREATE TABLE IF NOT EXISTS espaco_fisico (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    localizacao VARCHAR(255),
    capacidade INT NOT NULL,
    tipo VARCHAR(50) NOT NULL, -- AUDITORIO, SALA_DE_AULA, etc.
    disponivel BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS reserva (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    espaco_id BIGINT NOT NULL,
    data_hora_inicio TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    data_hora_fim TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'PENDENTE', -- PENDENTE, APROVADA, REJEITADA, CANCELADA
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (espaco_id) REFERENCES espaco_fisico(id)
);

CREATE TABLE IF NOT EXISTS solicitacao (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    espaco_id BIGINT NOT NULL,
    data_reserva DATE NOT NULL,
    hora_reserva TIME WITHOUT TIME ZONE NOT NULL,
    data_solicitacao TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'PENDENTE', -- PENDENTE, APROVADA, REJEITADA
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (espaco_id) REFERENCES espaco_fisico(id)
);

CREATE TABLE IF NOT EXISTS avaliacao (
    id BIGSERIAL PRIMARY KEY,
    solicitacao_id BIGINT UNIQUE NOT NULL, -- Uma avaliação por solicitação
    gestor_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL, -- APROVADA, REJEITADA
    justificativa TEXT,
    data_avaliacao TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    FOREIGN KEY (solicitacao_id) REFERENCES solicitacao(id),
    FOREIGN KEY (gestor_id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS auditoria_usuario (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    acao VARCHAR(255) NOT NULL,
    data_hora TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
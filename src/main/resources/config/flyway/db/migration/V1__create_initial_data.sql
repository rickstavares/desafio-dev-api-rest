CREATE TABLE PESSOA
(
    idPessoa                       bigint(20)     NOT NULL AUTO_INCREMENT,
    nome                           varchar(255)   NOT NULL,
    cpf                            varchar(11)    NOT NULL,
    dataNascimento                 date           NOT NULL,
    PRIMARY KEY (idPessoa)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;


CREATE TABLE CONTA
(
    idConta                     bigint(20) NOT NULL AUTO_INCREMENT,
    idPessoa                    bigint(20) NOT NULL,
    saldo                       decimal(13,2) NOT NULL,
    limiteSaqueDiario           decimal(13,2) NOT NULL,
    flagAtivo                   bit(1) NOT NULL DEFAULT true,
    tipoConta                   int(10) NOT NULL,
    dataCriacao                 date NOT NULL,
    PRIMARY KEY (idConta),
    CONSTRAINT fk_conta_pessoa FOREIGN KEY (idPessoa) REFERENCES PESSOA (idPessoa)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;


CREATE TABLE TRANSACAO
(
    idTransacao                 bigint(20) NOT NULL AUTO_INCREMENT,
    idConta                     bigint(20) NOT NULL,
    valor                       decimal(13,2) NOT NULL,
    dataTransacao               date NOT NULL,
    PRIMARY KEY (idTransacao),
    CONSTRAINT fk_transacao_conta FOREIGN KEY (idConta) REFERENCES CONTA (idConta)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

INSERT INTO PESSOA VALUES (1, 'Mickey', '11111111111', '1990-01-01');
INSERT INTO PESSOA VALUES (2, 'Pateta', '22222222222', '1890-01-01');
INSERT INTO PESSOA VALUES (3, 'Pluto', '33333333333', '1999-01-01');
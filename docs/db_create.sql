CREATE SEQUENCE cod_pessoa_seq;
CREATE SEQUENCE cod_equipe_seq;

CREATE TABLE Pessoas (
	cod_pessoa INT UNIQUE,
	nome VARCHAR(40),
	documento VARCHAR(15) UNIQUE,
	data_nascimento DATE,
	CONSTRAINT pk_pessoa PRIMARY KEY (cod_pessoa)
);

CREATE TABLE Equipes (
	cod_equipe INT UNIQUE,
	nome varchar(40)
);

CREATE TABLE Atletas (
	cod_atleta INT UNIQUE,
	peso NUMERIC(5, 2),
	altura NUMERIC(4, 3),
	sexo INT,
	cidade VARCHAR(40),
	estado VARCHAR(2),
	pais VARCHAR(3),
	titular BOOLEAN,
	funcao VARCHAR(15),
	cod_equipe INT,
	CONSTRAINT pk_atleta PRIMARY KEY (cod_atleta),
	CONSTRAINT fk_pessoa FOREIGN KEY (cod_atleta) REFERENCES Pessoas (cod_pessoa),
	CONSTRAINT fk_equipe FOREIGN KEY (cod_equipe) REFERENCES Equipes (cod_equipe)
);

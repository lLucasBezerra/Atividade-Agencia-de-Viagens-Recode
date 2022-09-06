CREATE DATABASE AgenciaViagens;
USE AgenciaViagens;

CREATE TABLE cliente(
codCli int NOT NULL AUTO_INCREMENT PRIMARY KEY,
cpf VARCHAR(11),
origem VARCHAR(20),
dataVolta DATE,
dataIda DATE
);
select * from cliente;

CREATE TABLE escolher(
fk_codCli INT,
fk_codDest INT,
fk_codVoo INT);
select * from escolher;

CREATE TABLE destinos(
codDest INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
paises VARCHAR(25),
cidade  VARCHAR(20),
obraR VARCHAR(60)
);
select * from destinos;

CREATE TABLE voo(
codVoo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
companhiaV VARCHAR(20) NOT NULL,
preco DECIMAL(7,2) NOT NULL);
select * from voo;

CREATE TABLE promocoes(
codPromo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nomePromo VARCHAR(25),
desconto TINYINT,
novoPreco DECIMAL(7,2) NOT NULL);
select * from promocoes;

INSERT INTO destinos VALUES(null, "Italia","Cinque-Terre","Luca");
INSERT INTO destinos VALUES(null, "Italia","Paris","Ratatoulle");
INSERT INTO destinos VALUES(null, "Italia","Sicilia","Poderoso Chefão");
INSERT INTO destinos VALUES(null, "Irlanda","Condado de Down","Game of Thrones");
INSERT INTO destinos VALUES(null, "Georgia","Atlanta","Stranger Things");
INSERT INTO destinos VALUES(null, "Estados Unidos","Los Angeles","Sitcoms em geral");


ALTER TABLE destinos ADD FOREIGN KEY (fk_codPromo) REFERENCES promocoes(codPromo);
ALTER TABLE escolherdestinocompanhia ADD FOREIGN KEY (fk_codCli) REFERENCES cliente(codCli);
ALTER TABLE escolherdestinocompanhia ADD FOREIGN KEY (fk_codDest) REFERENCES destinos(codDest);
ALTER TABLE escolherdestinocompanhia ADD FOREIGN KEY (fk_codVoo) REFERENCES voo(codVoo);

INSERT INTO voo VALUES (NULL, "LATAM Airlines", 2500.00);
INSERT INTO voo VALUES (NULL, "Avianca", 2300.00);
INSERT INTO voo VALUES (NULL, "Delta Airlines", 3100.00);

UPDATE destino SET fk_codPromo = 3 WHERE codDest=7;
insert into escolher VALUES(10, 3,2);
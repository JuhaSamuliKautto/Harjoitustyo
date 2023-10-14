SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS tyyppi; 
DROP TABLE IF EXISTS ajoneuvo; 
DROP TABLE IF EXISTS usertable;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE tyyppi
(id BIGINT NOT NULL AUTO_INCREMENT 
, nimi VARCHAR(100) NOT NULL
,PRIMARY KEY (id)
);
INSERT INTO tyyppi (nimi) 
VALUES ("Vetoauto")
, ("Perävaunu")
, ("Puoliperävaunu")
, ("Jakeluauto")
, ("Traktori");


CREATE TABLE ajoneuvo (
id BIGINT NOT NULL AUTO_INCREMENT
, rekisteritunnus VARCHAR(50) NOT NULL
, merkki VARCHAR(50) NOT NULL
, malli VARCHAR(100) NOT NULL
, vuosi INT NOT NULL
, tyyppi_id BIGINT
, PRIMARY KEY (id)
, FOREIGN KEY (tyyppi_id) REFERENCES tyyppi(id)
);
INSERT INTO ajoneuvo(rekisteritunnus, merkki, malli, vuosi, tyyppi_id) 
VALUES ("XLK-987", "Volvo", "FH13 2019 500 6X2 Väliteli", 2019, 1), 
("GGG-169", "Mercedez-Benz", "Actros", 2020, 4);


CREATE TABLE usertable
(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
, username VARCHAR(100) NOT NULL
, passwordHash VARCHAR(100) NOT NULL
, role  VARCHAR(100) NOT NULL
);
INSERT INTO usertable (username, passwordHash, role) 
VALUES ("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER"), 
("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");


SELECT * FROM tyyppi;
SELECT * FROM ajoneuvo;
SELECT * FROM usertable;

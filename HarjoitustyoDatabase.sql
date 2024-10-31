DROP TABLE IF EXISTS henkilo;
DROP TABLE IF EXISTS hevonen;
DROP TABLE IF EXISTS viesti;

CREATE TABLE henkilo
(henkiloid BIGSERIAL PRIMARY KEY
, username VARCHAR(100) NOT NULL
, password_hash VARCHAR(100) NOT NULL 
, etunimi VARCHAR(100) NOT NULL
, sukunimi VARCHAR(100) NOT NULL
, puhelin VARCHAR(20) NOT NULL
, sahkoposti VARCHAR(100)
, user_role VARCHAR(20) NOT NULL
);

INSERT INTO henkilo (username, password_hash, etunimi, sukunimi, puhelin, sahkoposti, user_role)
VALUES ('user', '$2a$10$aYzIugkyZ/U18AMCCSbpSuptM096SGxTWYX5YqzOUqXfU8GXOxOnK', 'Liisa', 'Lopponen', '040-98765432', 'liisa.lopponen@osoite.com', 'USER'),
('admin', '$2a$10$0biol8FjOcs3mBRTfRgNqOauNXFPWNuOacp9oAPNhImMHUtVgteOW', 'Maija', 'Miikkulainen', '050-4561237', 'maija.m@sahkoposti.com', 'ADMIN'),
('user2', '$2a$10$dMksj5T2YXRSnIlKM/uoauHyrrMdAC4G5pqxOWKkfkYbH52asLmcu', 'Matti', 'Muttonen', '040-1234667', 'mattimuttonen@mailiosoite.fi', 'USER');

CREATE TABLE hevonen
(hevosid BIGSERIAL PRIMARY KEY
, nimi VARCHAR(100) NOT NULL
, rotu VARCHAR(30) NOT NULL
, syntvuosi INTEGER
, henkiloid BIGINT,
FOREIGN KEY (henkiloid) REFERENCES Henkilo(henkiloid)
);

INSERT INTO hevonen(nimi, rotu, syntvuosi, henkiloid)
VALUES('Polle', 'Suomenhevonen', 2015, 2),
('Tessa', 'Puoliverinen', 2013, 3),
('Nero', 'Haflinger', 2020, 2),
('Donna', 'Connemara', 2016, 1);

CREATE TABLE viesti
(viesti_id BIGSERIAL PRIMARY KEY
, teksti VARCHAR(100) NOT NULL
, paivamaara TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
, hevosid BIGINT
, henkiloid BIGINT
, FOREIGN KEY (henkiloid) REFERENCES Henkilo(henkiloid)
, FOREIGN KEY (hevosid) REFERENCES Hevonen(hevosid)
);

INSERT INTO viesti(teksti, paivamaara, hevosid, henkiloid)
VALUES('Muista laittaa Pollelle loimi ulos', NOW(), 2, 2),
('Nero ontuu vasenta etujalkaa, joten ei saa ratsastaa', NOW(), 3, 2),
('Tessan etujalassa haava, puhdistus ohjeen mukaan', NOW(), 1, 1),
('Neron kaura-annos puolitettu toistaiseksi', NOW(), 3, 3),
('Muista siivota Donnan tarha kunnolla', NOW(), 4, 1);

SELECT * FROM henkilo;
SELECT * FROM hevonen;
SELECT * FROM viesti;
CREATE SCHEMA alianza;

DROP TABLE IF EXISTS alianza.cliente;

CREATE TABLE alianza.cliente
(
    shared_key varchar NOT NULL,
    name varchar NOT NULL,
    email varchar NOT NULL,
    phone varchar NOT NULL,
    date_added date NOT NULL,
    date_finish date,
    PRIMARY KEY (shared_key)
) WITHOUT OIDS;



INSERT INTO alianza.cliente
(shared_key, "name", email, phone, date_added, date_finish)
VALUES('jcastro', 'Johan Castro', 'johanc.ing@gmail.com', '3227026344', '2024-10-15', now());
INSERT INTO alianza.cliente
(shared_key, "name", email, phone, date_added, date_finish)
VALUES('palianza', 'Prueba Alianza', 'prueba.alianza@yptmail.com', '3229876542', now(), NULL);



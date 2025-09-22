CREATE DATABASE zoologico_db;
USE zoologico_db;

CREATE TABLE especies (
    id_especie INT(11) NOT NULL AUTO_INCREMENT,
    nombre_vulgar VARCHAR(100) NOT NULL,
    nombre_cientifico VARCHAR(100) NOT NULL,
    familia VARCHAR(200),
    peligro_extincion BIT(1) DEFAULT 0,
    PRIMARY KEY (id_especie)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE zoologicos (
    id_zoologico INT(11) NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    pais VARCHAR(100) NOT NULL,
    fecha_inauguracion DATE,
    PRIMARY KEY (id_zoologico)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE animales (
    id_animal INT(11) NOT NULL AUTO_INCREMENT,
    identificacion VARCHAR(20) NOT NULL,
    sexo ENUM('Macho', 'Hembra') NOT NULL,
    anio_nacimiento INT(10),
    id_especie INT(11) NOT NULL,
    id_zoologico INT(11) NULL,
    PRIMARY KEY (id_animal),
    FOREIGN KEY (id_especie) REFERENCES especies(id_especie)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    FOREIGN KEY (id_zoologico) REFERENCES zoologicos(id_zoologico)
        ON UPDATE CASCADE
        ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


ALTER TABLE animales MODIFY COLUMN sexo ENUM('MACHO', 'HEMBRA') NOT NULL;
ALTER TABLE animales MODIFY COLUMN id_especie INT NULL;
ALTER TABLE animales MODIFY COLUMN id_zoologico INT NULL;

INSERT INTO especies (nombre_vulgar, nombre_cientifico, familia, peligro_extincion) VALUES
('León', 'Panthera leo', 'Felidae', false),
('Elefante africano', 'Loxodonta africana', 'Elephantidae', true),
('Pingüino emperador', 'Aptenodytes forsteri', 'Spheniscidae', true),
('Cebra de Grant', 'Equus quagga boehmi', 'Equidae', false);

INSERT INTO zoologicos (nombre, ciudad, pais, fecha_inauguracion) VALUES
('Zoo Nacional', 'Ciudad de México', 'México', '1924-07-06'),
('Bioparque Estrella', 'Estado de México', 'México', '1995-02-12'),
('Africam Safari', 'Puebla', 'México', '1972-04-02'),
('Acuario Inbursa', 'Ciudad de México', 'México', '2014-06-11'),
('Zoológico Guadalajara', 'Guadalajara', 'México', '1988-03-25');

INSERT INTO animales (identificacion, sexo, anio_nacimiento, id_especie, id_zoologico) VALUES
('L-001', 'MACHO', 2020, 1, 1),
('L-002', 'HEMBRA', 2019, 1, 1),
('EA-001', 'MACHO', 2022, 2, 2),
('EA-002', 'HEMBRA', 2021, 2, 2),
('PE-001', 'MACHO', 2023, 3, 3),
('PE-002', 'HEMBRA', 2023, 3, 3),
('ZG-001', 'MACHO', 2021, 4, 1),
('ZG-002', 'HEMBRA', 2020, 4, 2);


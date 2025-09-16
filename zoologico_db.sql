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

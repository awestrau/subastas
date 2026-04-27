CREATE DATABASE IF NOT EXISTS db_subastas;
USE db_subastas;

CREATE TABLE t_moderadores(
    id VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    correo VARCHAR(100) NOT NULL
);

CREATE TABLE t_coleccionistas(
    id VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    correo VARCHAR(100) NOT NULL,
    puntuacion INT DEFAULT 0,
    direccion VARCHAR(255) NOT NULL
);

CREATE TABLE t_vendedores(
    id VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    correo VARCHAR(100) NOT NULL,
    puntuacion INT DEFAULT 0,
    direccion VARCHAR(255) NOT NULL
);

CREATE TABLE t_objetos(
    id_objeto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(25) NOT NULL,
    descripcion TEXT NOT NULL,
    estado VARCHAR(15) NOT NULL,
    fecha_compra DATE NOT NULL,
    antiguedad VARCHAR(25) NOT NULL
);

CREATE TABLE t_subastas(
    id_subasta INT AUTO_INCREMENT PRIMARY KEY,
    fecha_vencimiento DATE NOT NULL,
    tiempo_para_vencer VARCHAR(25) NOT NULL,
    creador_id VARCHAR(50) NOT NULL,
    calificacion_creador DECIMAL(5,2) NOT NULL,
    precio_minimo DECIMAL(10,2) NOT NULL,
    vigente BOOLEAN NOT NULL
);

CREATE TABLE t_subasta_objetos(
    id_subasta INT NOT NULL,
    id_objeto INT NOT NULL,
    PRIMARY KEY (id_subasta, id_objeto),
    FOREIGN KEY (id_subasta) REFERENCES t_subastas(id_subasta),
    FOREIGN KEY (id_objeto) REFERENCES t_objetos(id_objeto)
);

CREATE TABLE t_ofertas(
    id_oferta INT AUTO_INCREMENT PRIMARY KEY,
    id_subasta INT NOT NULL,
    id_oferente VARCHAR(50) NOT NULL,
    puntuacion_oferente DECIMAL(5,2) NOT NULL,
    precio_ofertado DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_subasta) REFERENCES t_subastas(id_subasta),
    FOREIGN KEY (id_oferente) REFERENCES t_coleccionistas(id)
);

CREATE TABLE t_ordenes_adjudicacion(
    id_orden INT AUTO_INCREMENT PRIMARY KEY,
    id_ganador VARCHAR(50) NOT NULL,
    id_subasta INT NOT NULL,
    id_oferta INT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP
);
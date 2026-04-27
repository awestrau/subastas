CREATE DATABASE db_subastas;
USE db_subastas;

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

CREATE TABLE t_ofertas(
    id_oferta INT AUTO_INCREMENT PRIMARY KEY,
    id_subasta INT NOT NULL,
    id_oferente VARCHAR(50) NOT NULL,
    puntuacion_oferente DECIMAL(5,2) NOT NULL,
    precio_ofertado DECIMAL(10,2) NOT NULL
);

CREATE TABLE t_ordenes_adjudicacion(
    id_orden INT AUTO_INCREMENT PRIMARY KEY,
    nombre_ganador VARCHAR(100) NOT NULL,
    fecha_orden DATE NOT NULL,
    precio_total DECIMAL(10,2) NOT NULL
);

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
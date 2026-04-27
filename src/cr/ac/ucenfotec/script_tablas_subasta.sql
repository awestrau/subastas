CREATE DATABASE db_subastas;
USE db_subastas;

CREATE TABLE t_objetos(
    id_objeto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(25) NOT NULL,
    descripcion TEXT NOT NULL,
    estado VARCHAR(15) NOT NULL,
    fecha_compra DATETIME NOT NULL,
    antiguedad VARCHAR(25) NOT NULL
);

CREATE TABLE t_subastas(
    id_subasta INT AUTO_INCREMENT PRIMARY KEY,
    fecha_vencimiento DATETIME NOT NULL,
    tiempo_para_vencer VARCHAR(25) NOT NULL,
    creador_id VARCHAR(50) NOT NULL,
    calificacion_creador DECIMAL(5,2) NOT NULL,
    precio_minimo DECIMAL(10,2) NOT NULL,
    vigente BOOLEAN NOT NULL
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
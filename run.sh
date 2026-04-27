#!/bin/bash
# Compilar todo el código fuente, incluyendo la librería JDBC en el classpath (si existe en lib/)
mkdir -p bin
javac -cp "lib/*:src" -d bin $(find src -name "*.java")

# Ejecutar el programa principal con la librería en el classpath
java -cp "lib/*:bin" cr.ac.ucenfotec.Main

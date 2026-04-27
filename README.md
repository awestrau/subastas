# Proyecto Subastas

## Universidad Cenfotec

## Programación Orientada a Objetos

### Estudiantes: 
- Andrés Westra Ureña
- Alexander de Jesús González Castillo

### Profesor
- Romario Salas Cerdas

## Instrucciones de Ejecución

### 1. Base de Datos (MySQL)
Es necesario tener en ejecución la base de datos MySQL. Asegúrese de haber importado el esquema inicial usando el script provisto en `src/cr/ac/ucenfotec/script_tablas_subasta.sql`:
```bash
mysql -u root -p < src/cr/ac/ucenfotec/script_tablas_subasta.sql
```

### 2. Configuración JDBC
Para que el proyecto se conecte a MySQL, debe colocar el driver `mysql-connector-java.jar` dentro de la carpeta `lib/` en la raíz del proyecto (cree la carpeta si aún no existe).

### 3. Compilación y Ejecución
En sistemas Linux/macOS, puede utilizar el script automatizado `run.sh` para compilar y ejecutar el proyecto de inmediato.

Otorgue permisos de ejecución al script (solo la primera vez):
```bash
chmod +x run.sh
```

Ejecute el sistema:
```bash
./run.sh
```
El script automáticamente compilará todas las clases `.java` depositándolas en la carpeta `bin/` y arrancará la clase principal (`Main`) incluyendo el conector MySQL.

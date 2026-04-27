# Proyecto Subastas

## Universidad Cenfotec

## Programación Orientada a Objetos

### Estudiantes: 
- Andrés Westra Ureña
- Alexander de Jesús González Castillo

### Profesor
- Romario Salas Cerdas

## Instrucciones de Ejecución

### 1. Base de Datos (MySQL) vía Docker
El proyecto incluye un archivo `docker-compose.yml` ya configurado con las credenciales que se encuentran en el archivo `bd.properties` (usuario `root`, contraseña `4280zdvd`, base de datos `db_subastas`).

Para arrancar el motor de base de datos junto con la creación automática de todas sus tablas, ejecute en la terminal desde la raíz del proyecto:
```bash
docker-compose up -d
```
> **Nota:** La primera vez que lo ejecute tomará unos instantes descargar la imagen e inicializar el script SQL automáticamente. Espere un momento antes de ejecutar el programa principal.

Para detener y limpiar el contenedor al finalizar sus pruebas, puede usar:
```bash
docker-compose down
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

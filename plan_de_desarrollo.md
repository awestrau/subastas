# Plan de Desarrollo: Primer Avance - Plataforma de Subastas

Este documento presenta una propuesta de distribución de trabajo justa y equitativa para el desarrollo del primer avance del proyecto, asignando responsabilidades específicas para Andrés y Alex. 

Se incluyen también las indicaciones claras sobre lo que se debe implementar de acuerdo con la consigna, separando estructuralmente las funciones para evitar conflictos y asegurar el progreso paralelo.

---

## 🏗️ 1. División del Trabajo

### **Responsabilidades Compartidas**
* **Revisión de Código y UML:** Ambos deben revisar el código del otro para asegurar buenas prácticas, encapsulamiento correcto y arquitectura por capas.
* **Documentación:** Cada uno debe generar el **Javadoc** correspondiente a las clases y métodos que programe.
* **Gestión de Versiones:** Ambos deben subir (`commit` y `push`) regularmente sus avances al repositorio de GitHub correspondiente, manteniendo un historial claro.

### **👨‍💻 Asignaciones para Andrés**

**1. Configuración del Entorno y Arquitectura base**
* Inicializar los dos proyectos base (Lógica de Negocio e Interfaz Gráfica).
* Configurar los repositorios en GitHub y dar acceso al equipo.
* Establecer la estructura de paquetes obligatoria (ej. el paquete raíz `cr.ac.ucenfotec`).

**2. Modelado UML (Parte 1: Usuarios y Dominio Base)**
* Diagramar las clases referentes a `Usuario` (junto con sus posibles especializaciones/roles si se requiere, según la consigna: Moderador, Vendedor, Coleccionista) y las relaciones pertinentes entre ellos.

**3. Desarrollo de la Capa Lógica (Usuarios y Objetos)**
* Implementar las clases base para la gestión de usuarios.
* Cumplir con la estructura solicitada: atributos privados, constructor por defecto, constructor parametrizado general, métodos *getters/setters* y método `toString()`.
* Implementar la lógica y persistencia en memoria (basada en colecciones como `ArrayList`) para los usuarios.
* Programar las **Reglas de Negocio** correspondientes: 
  * Verificación de existencia del moderador único.
  * Validaciones de mayoría de edad para el registro.

**4. Capa de Presentación (Menú General y Usuarios)**
* Crear el esqueleto inicial del menú principal de consola.
* Implementar las opciones del entorno visual (UI) para:
  * Registro de usuarios (solicitando los datos base por consola).
  * Listado de usuarios.

---

### **👨‍💻 Asignaciones para Alex**

**1. Modelado UML (Parte 2: Subastas, Ofertas e Integración)**
* Diagramar las clases de `Subasta` y `Oferta`.
* Integrar sus clases con el diagrama creado por Andrés, estableciendo las relaciones correctas (asociaciones, agregaciones, etc.) para entregar un único archivo `.pdf` con el UML completo.

**2. Desarrollo de la Capa Lógica (Subastas y Ofertas)**
* Implementar las clases de gestión para `Subasta` y `Oferta`.
* Cumplir con la estructura obligatoria: atributos encapsulados, doble constructor (defecto y parámetros generales), *getters/setters* y el método `toString()`.
* Implementar la persistencia en memoria (ej. `ArrayList`) para guardar el registro de subastas y las ofertas recibidas en cada una de ellas.
* Programar las **Reglas de Negocio** correspondientes:
  * Evitar creaciones de subastas sin objetos asociados.
  * Validar que moderadores y vendedores no puedan hacer ofertas.
  * Comprobar que un coleccionista no puje en su propia subasta.

**3. Capa de Presentación (UI de Subastas y Ofertas)**
* Acoplarse al menú principal construido por Andrés y desarrollar las lógicas de recolección de datos por consola para:
  * Creación de subastas.
  * Listado de subastas activas o registradas.
  * Creación de ofertas económicas.
  * Listado de ofertas.

---

## 📋 2. Indicaciones Claras de Hardware y Software (Checklist de Implementación)

### 📌 Sobre la Arquitectura
* **Separación de Capas Obligatoria:** Deben existir dos proyectos lógicamente separados: uno exclusivamente visual (UI, entrada/salidas por consola) y uno de pura lógica e información (Reglas de negocio y clases orientadas a objetos).
* **Ausencia de Instanciación Indebida:** La interfaz gráfica (UI) **no debe** encargarse de crear, manejar, ni contener lógicamente a los objetos de negocio (como usar `new Usuario()` irresponsablemente en la visual); eso deberá ser estrictamente delegado a un controlador, manejador o clase equivalente de la capa lógica.

### 📌 Sobre Clases y Objetos
* Para todas las clases desarrolladas deben estar presentes:
  * **Encapsulamiento estricto:** Todo atributo debe ser `private`, accediéndose solo vía métodos de acceso públicos (`getters` y `setters`).
  * **Dos constructores:** Al menos un constructor vacío/por defecto, y uno parametrizado que reciba y asigne todos los campos relevantes de la clase.
  * **Sobrescritura de `toString()`:** Adaptándolo para que muestre el estado importante y legible del objeto cuando se mande a imprimir.

### 📌 Sobre la Persistencia
* Las bases de datos no están permitidas (ni archivos) en esta etapa para la lógica principal. Todo registro creado o actualizado a lo largo de la ejecución del programa deberá almacenarse temporalmente utilizando listas en memoria (`ArrayList`).

### 📌 Sobre el Modelado UML
* Al final, deberán exportar su modelo en formato PDF. Asegúrense de usar consistentemente la notación universal (símbolos de +, -, #, líneas sólidas/punteadas para relaciones, diamantes en caso de composición o agregación, flechas de herencia, etc.).

### 📌 Sobre las Reglas de Negocio Base a Validar (Importantes)
1. Antes de dejar hacer nada o en primer instanciamiento, forzar el registro del Moderador si no existe (hay que contemplar que solo hay un Moderador en todo el sistema y que este último es inhabilitado para actuar en subastas).
2. Para que alguien sea Coleccionista, vendedor o Moderador, en los datos requeridos (fecha de nacimiento / edad) se debe verificar que sea legalmente mayor de edad.
3. Un "Coleccionista" no oferta en subastas que él mismo levantó.
4. Para que exista una "Subasta", obligatoriamente hay que haberle pasado el(los) objeto(s) que se rematan, de lo contrario la acción de creación debe ser impedida por el sistema.
5. El sistema de paquetes debe respetar de manera inquebrantable que su estructura de directorio/librería sea de tipo jerárquica (p.ej.: `cr.ac.ucenfotec.dl.clases`, `cr.ac.ucenfotec.tl.controlador`, etc.).

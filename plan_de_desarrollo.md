# Plan de Desarrollo: Plataforma de Subastas - Primer Avance

**Integrantes:** Andrés y Alex
**Fecha de Entrega:** 15 de marzo del 2026, 11:59 pm.
**Objetivo:** Desarrollar la estructura básica, el diagrama UML y un menú de consola funcional respetando la arquitectura de capas.

---

## Fase 1: Configuración Inicial y Arquitectura (Trabajo Conjunto)
*Antes de tirar código, deben dejar la base lista para evitar conflictos en GitHub.*

* **Andrés:** Crear dos packages separados en su IDE: uno para la interfaz gráfica y otro para la capa lógica. 
* **Andrés:** Configurar el repositorio en GitHub y dar acceso a Alex.
* **Alex:** Crear la estructura base de paquetes en ambos proyectos, asegurándose de que el paquete raíz sea `cr.ac.ucenfotec`.
* **Alex:** Crear la clase "Gestor" o "Controlador" en la capa lógica, la cual servirá de puente para que la interfaz gráfica no instancie objetos de negocio.
* **Ambos:** Definir que toda la persistencia de datos se manejará temporalmente usando `ArrayLists` en la memoria de este Gestor.

---

## Fase 2: Diagrama UML y Capa Lógica (División de Tareas)
*Aquí se construyen las entidades (clases del dominio). Toda clase debe tener sus atributos privados (encapsulamiento), dos constructores (uno por defecto y uno con todos los parámetros) y su método `toString()`.

### Tareas de Andrés (Módulo de Usuarios y Objetos)
* **Diagrama UML:** Confeccionar el diagrama UML de las clases `Usuario`, `Subastas` y `Ofertas`, asegurando que se muestren atributos, métodos y relaciones.
* **Clase Usuario y Subclases:** Implementar la clase base `Usuario` y aplicar herencia para las clases hijas: Moderador, Vendedor y Coleccionista.
* **Atributos de Usuarios:** Asegurar que todos tengan nombre completo, identificación, fecha de nacimiento, edad calculada, contraseña y correo electrónico.
* **Atributos Específicos:** Agregar la puntuación y dirección al Vendedor y Coleccionista. Al Coleccionista, agregarle las listas de intereses y objetos de su propiedad.
* **Clase Objeto:** Implementar la clase de los objetos ofrecidos con su nombre, descripción, estado, fecha de compra y antigüedad calculada.

### Tareas de Alex (Módulo de Subastas y Transacciones)
* **Clase Subasta:** Implementar la clase que maneje fecha de vencimiento, tiempo restante, usuario creador, puntuación del creador, precio mínimo, conjunto de objetos subastados y estado.
* **Clase Oferta:** Implementar la propuesta económica, que incluya el nombre del oferente, su puntuación y el precio ofertado.
* **Clase Orden de Adjudicación:** Implementar la orden con el nombre del ganador, fecha, detalle de objetos adjudicados y precio total.

---

## Fase 3: Reglas de Negocio y Presentación (UI)
*Aquí se desarrolla el menú de consola y las validaciones en la capa lógica. Recuerden que la UI solo pide y muestra datos; el Gestor es quien valida y guarda.*

### Tareas de Andrés (Menú de Usuarios y Validaciones)
* **UI - Usuarios:** Programar las opciones del menú de consola para "Registro de usuarios" y "Listado de usuarios".
* **Validaciones (en el Gestor):** * Verificar al iniciar si existe un moderador; si no, pedir datos para registrarlo.
    * Validar que solo exista un único moderador en la plataforma.
    * Validar que para registrarse (Moderador, Vendedor o Coleccionista) el usuario debe ser mayor de edad.

### Tareas de Alex (Menú de Subastas/Ofertas y Validaciones)
* **UI - Subastas y Ofertas:** Programar las opciones del menú para "Creación de subastas", "Listado de subastas", "Creación de ofertas" y "Listado de ofertas".
* **Validaciones (en el Gestor):**
    * Impedir que el moderador participe en subastas o realice ofertas.
    * Impedir que el vendedor realice ofertas.
    * Validar que el creador de una subasta (si es coleccionista) no pueda pujar/ofertar en ella.
    * Asegurar que no se pueda crear una subasta vacía (sin objetos asociados).
    * Validar que un coleccionista solo pueda subastar objetos que ya estén registrados en su colección.

---

## Fase 4: Integración y Entregables Finales (Trabajo Conjunto)
* **Ambos:** Generar la documentación Javadoc de todas las clases y librerías que crearon.
* **Andrés:** Exportar el diagrama UML final en formato `.pdf`.
* **Alex:** Asegurar que todo el código `.java` esté correctamente subido mediante *commits* a los repositorios de GitHub.
* **Ambos:** Realizar pruebas de escritorio usando el menú para comprobar que todas las opciones funcionen sin caerse.
* **Representante del grupo:** Subir el archivo `.pdf` a la plataforma Moodle y entregar los enlaces de GitHub en el tiempo y espacio indicado por el docente.

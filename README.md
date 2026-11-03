# Agenda Telefónica MVC

Aplicación de escritorio para gestionar una agenda telefónica, desarrollada en **Java Swing** con **Gradle** y el patrón arquitectónico **Modelo – Vista – Controlador (MVC)**. Es el proyecto del **Hackathon 2 de Generation Colombia**.

##  Misión del reto

Crear una aplicación de escritorio para la gestión de una agenda telefónica, con tres condiciones obligatorias:

1. Implementarla en **Java Swing**.
2. Usar **Gradle** como gestor de automatización desde el inicio del proyecto.
3. Respetar estrictamente el patrón **MVC**.

### ¿Por qué Gradle?

- **Portabilidad:** gracias al Gradle Wrapper (`gradlew`), el proyecto compila y funciona igual en el equipo de cada integrante o en el de quien lo evalúe, sin depender de la máquina.
- **Extensibilidad segura:** la configuración vive en `build.gradle`, de modo que agregar librerías externas (por ejemplo, para persistencia) se hace de forma limpia y automática desde repositorios remotos, sin manipular archivos `.jar` a mano.

## Funcionalidades

- **Añadir contacto** (nombre, apellido y teléfono).
- **Buscar** el teléfono de un contacto por nombre y apellido.
- **Modificar el teléfono** de un contacto existente.
- **Eliminar** el contacto seleccionado en la tabla.
- **Limpiar** el formulario.
- Tabla de contactos **ordenada alfabéticamente por nombre**.
- **Sincronización:** al seleccionar una fila, sus datos se cargan en el formulario para editarlos o borrarlos rápido.
- Indicador de **capacidad máxima** y **espacios libres**.
- **Panel de estado:** los errores de validación (campos vacíos, teléfono con formato incorrecto, agenda llena o contacto duplicado) se muestran en **rojo** y las operaciones exitosas en **verde**, sin lanzar excepciones a la consola.

##  Reglas de negocio

| Regla | Detalle |
|-------|---------|
| Atributos | `nombre`, `apellido` y `telefono`. |
| Campos obligatorios | El nombre y el apellido no pueden estar vacíos ni contener solo espacios en blanco. |
| Igualdad | Dos contactos son iguales si tienen el mismo nombre y apellido, ignorando mayúsculas y minúsculas (`equals()` y `hashCode()` sobrescritos). |
| Duplicados | No se permiten contactos duplicados. |
| Capacidad | Máximo de **10 contactos** por defecto, configurable al crear la agenda con `new Agenda(int capacidadMaxima)`. |
| Teléfono | Se valida con expresión regular: 9 o 10 dígitos seguidos, con prefijo internacional opcional (por ejemplo `3001234567` o `+57 3001234567`). |
| Almacenamiento | Los contactos se guardan en memoria en una `List`; se pierden al cerrar la aplicación. |

##  Arquitectura

```
├── modelo/
│ ├── Contacto.java <── Datos puros de la entidad
│ └── Agenda.java <── Lógica de negocio (Array/List de
contactos)
│
├── vista/
│ ├── VentanaPrincipal.java <── JFrame y construcción de componentes
Swing
│ └── ComponentesGraficos.java<── Opcional: paneles o tablas
personalizadas
│
├── controlador/
│ └── AgendaControlador.java <── Escucha los eventos de la vista y
coordina el modelo
│
└── Main.java <── Orquestador: Instancia el Modelo, la
Vista y los conecta```

**Regla de desacoplamiento**

- Las clases del paquete `modelo` **no importan nada de `javax.swing.*`**.
- Las clases del paquete `vista` **no deciden qué ocurre al hacer clic**: solo exponen sus componentes mediante getters.
- El `controlador` asigna los `ActionListener` a la vista y actualiza la tabla (`DefaultTableModel`) con los datos del modelo.

### Métodos de `Agenda`

| Método | Descripción |
|--------|-------------|
| `añadirContacto(Contacto c)` | Inserta el contacto si hay espacio y no está duplicado. |
| `existeContacto(Contacto c)` | Verifica si ya existe un contacto con ese nombre y apellido. |
| `listarContactos()` | Devuelve la lista de contactos ordenada alfabéticamente. |
| `buscarContacto(String nombre, String apellido)` | Devuelve el teléfono del contacto, o `null` si no existe. |
| `eliminarContacto(Contacto c)` | Elimina el contacto si existe. |
| `modificarTelefono(String nombre, String apellido, String nuevoTelefono)` | Actualiza el teléfono de un contacto existente tras validar el formato. |
| `AgendaLlena()` y `espaciosLibres()` | Informan el estado de la capacidad. |

##  Tecnologías

- Java 17 o superior
- Swing (`javax.swing`)
- Gradle con Gradle Wrapper
- JUnit configurado en `build.gradle` para pruebas

##  Cómo ejecutarlo

### Requisitos

- JDK 17 o superior
- Git

### Desde IntelliJ IDEA (recomendado)

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Diego1-1/Hackathon-2-Generation-Agenda-Telef-nica-MVC.git
   ```
2. Abre la carpeta como proyecto Gradle.
3. Ejecuta la clase `com.hackathon.agenda.Main`.

### Desde la terminal

```bash
cd Hackathon-2-Generation-Agenda-Telef-nica-MVC

# Compilar
./gradlew build            # En Windows: gradlew.bat build

# Ejecutar
java -cp build/classes/java/main com.hackathon.agenda.Main
```

## 📖 Guía de uso

1. Escribe **nombre**, **apellido** y **teléfono** y pulsa **Añadir contacto**.
2. Para **buscar**, escribe nombre y apellido y pulsa **Buscar**: el teléfono aparece en su campo.
3. Para **modificar**, indica nombre y apellido del contacto, escribe el nuevo número y pulsa **Modificar teléfono**.
4. Para **eliminar**, selecciona la fila en la tabla y pulsa **Eliminar**.
5. **Limpiar** vacía el formulario y quita la selección de la tabla.

## Posibles mejoras

- Persistencia de los contactos (archivo o base de datos), aprovechando que Gradle facilita incorporar librerías externas.
- Pruebas unitarias con JUnit para `Contacto` y `Agenda`.
- Aceptar números con espacios entre grupos de dígitos (por ejemplo `+34 600 000 000`).
- Confirmación antes de eliminar un contacto.

##  Equipo

Proyecto desarrollado por el equipo del Hackathon 2 de **Generation Colombia**.
Alan Melo
Diana Hinestroza
Diego Moreno
Estefany Hernandez
Pedro Saravia


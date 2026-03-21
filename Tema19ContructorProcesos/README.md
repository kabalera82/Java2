# Tema 19 — ProcessBuilder con Maven

> Tema 10 de 2ºDAM | Prerequisito: Process/ProcessBuilder (Tema 05), Maven basico

Proyecto Maven estructurado que profundiza en el uso de `ProcessBuilder` para crear, configurar y gestionar procesos del sistema operativo desde Java. Al estar en un proyecto Maven, el codigo se puede compilar y ejecutar facilmente desde cualquier IDE o desde la linea de comandos.

---

## Estructura del proyecto

```
Tema19ContructorProcesos/
├── pom.xml                                    <- Maven: Java 24, sin dependencias externas
└── src/main/java/
    ├── Ejercicio01.java                       <- ProcessBuilder basico: ejecutar un comando
    ├── Ejercicio02.java                       <- Capturar la salida del proceso
    ├── Ejercicio03.java                       <- Redirigir stderr a stdout
    ├── Ejercicio04.java                       <- Directorio de trabajo personalizado
    ├── Ejercicio05.java                       <- Variables de entorno del proceso hijo
    ├── Ejercicio06.java                       <- Guardar salida en fichero
    ├── Ejercicio07.java                       <- Lanzar aplicacion grafica (notepad / gedit)
    ├── ejercicio08.java                       <- Ejecutar segun el SO detectado
    ├── ejercicio09AbrirPrograma.java          <- Abrir un programa del sistema
    └── ejercicio10/
        ├── Ejercicio10IpconfigGuardarSalida.java <- Ejecutar ipconfig y guardar en fichero
        ├── GestorProcesos.java                <- Clase que gestiona el ciclo de vida de procesos
        ├── Main.java                          <- Punto de entrada del ejercicio 10
    └── ejercicio11/
        └── Main.java                          <- Procesos en paralelo con waitFor()
    └── ejercicio12/
        └── Main.java                          <- Menu interactivo de comandos
```

---

## Ejercicios en detalle

| Archivo | Concepto | Descripcion |
|---------|---------|-------------|
| `Ejercicio01` | `ProcessBuilder` basico | Ejecutar `cmd /c dir` y leer la salida linea a linea |
| `Ejercicio02` | Capturar stdout | Leer toda la salida con `BufferedReader` sobre `getInputStream()` |
| `Ejercicio03` | Mezclar stderr + stdout | `redirectErrorStream(true)` — un unico stream para toda la salida |
| `Ejercicio04` | Directorio de trabajo | `pb.directory(new File("ruta"))` para cambiar donde ejecuta el proceso |
| `Ejercicio05` | Variables de entorno | `pb.environment()` — anadir/modificar variables del proceso hijo |
| `Ejercicio06` | Redirigir a fichero | `pb.redirectOutput(new File("salida.txt"))` |
| `Ejercicio07` | Abrir programa GUI | Lanzar `notepad.exe` (Windows) o `gedit` (Linux) |
| `ejercicio08` | Detectar SO | `System.getProperty("os.name")` para elegir el comando correcto |
| `ejercicio09AbrirPrograma` | Abrir aplicacion del sistema | `Desktop.getDesktop().open(new File(...))` vs `ProcessBuilder` |
| `ejercicio10/` | Gestor de procesos | `GestorProcesos` encapsula lista de procesos, arranque, espera y cierre |
| `ejercicio11/` | Paralelo con `waitFor()` | Lanzar N procesos y esperar a que todos terminen |
| `ejercicio12/` | Menu interactivo | Bucle que ejecuta comandos segun la eleccion del usuario |

---

## Como compilar y ejecutar (Maven)

```bash
# Desde Tema19ContructorProcesos/
cd Tema19ContructorProcesos

# Compilar el proyecto
mvn compile

# Ejecutar un ejercicio especifico
mvn exec:java -Dexec.mainClass="Ejercicio01"
mvn exec:java -Dexec.mainClass="ejercicio10.Main"
```

### Sin Maven (compilacion manual)

```bash
javac -d out src/main/java/Ejercicio01.java
java -cp out Ejercicio01
```

---

## Notas

- El `pom.xml` usa Java 24. Si tienes una version anterior, cambia `maven.compiler.source` y `maven.compiler.target` a tu version.
- Los ejercicios que usan comandos como `cmd`, `dir`, `notepad` son especificos de Windows. En Linux/Mac adapta a `bash`, `ls`, `gedit`.
- `ejercicio08.java` (nombre en minuscula) — el nombre no sigue la convencion de Java para clases (`PascalCase`). No afecta al funcionamiento pero si a las buenas practicas.

---

## Referencia — `ProcessBuilder` completo

```java
ProcessBuilder pb = new ProcessBuilder();

// Configuracion
pb.command("cmd", "/c", "dir");               // comando
pb.directory(new File("C:/Users"));           // directorio de trabajo
pb.environment().put("DEBUG", "true");        // variable de entorno
pb.redirectErrorStream(true);                 // mezclar stderr con stdout
pb.redirectOutput(new File("out.txt"));       // redirigir a fichero
pb.inheritIO();                               // usar consola del proceso padre

// Ejecutar y esperar
Process proceso = pb.start();
int exitCode = proceso.waitFor();

// Leer salida (si NO se redirige a fichero)
try (BufferedReader br = new BufferedReader(
        new InputStreamReader(proceso.getInputStream()))) {
    br.lines().forEach(System.out::println);
}
```

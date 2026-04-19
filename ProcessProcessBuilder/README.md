# Process y ProcessBuilder — Ejecucion de procesos del SO

> Tema 05 de 2ºDAM | Prerequisito: Hilos (Tema 01)

`Process` y `ProcessBuilder` permiten a Java **lanzar y controlar procesos del sistema operativo** desde codigo: comandos de shell, aplicaciones externas, scripts. Son la interfaz entre la JVM y el SO.

---

## Teoria rapida

### `Runtime.exec()` vs `ProcessBuilder`

| | `Runtime.exec()` | `ProcessBuilder` |
|--|-----------------|-----------------|
| API | Antigua (Java 1.0) | Moderna (Java 5+) |
| Configuracion | Limitada | Completa (entorno, directorio, I/O) |
| Recomendada | Solo para casos simples | **Siempre que necesites control** |

### `ProcessBuilder` — uso basico

```java
ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir");  // Windows
ProcessBuilder pb = new ProcessBuilder("ls", "-la");          // Linux/Mac

// Configuracion
pb.directory(new File("C:/ruta"));              // directorio de trabajo
pb.environment().put("MI_VAR", "valor");        // variables de entorno
pb.redirectOutput(new File("salida.txt"));      // redirigir stdout a fichero
pb.redirectErrorStream(true);                   // mezclar stderr con stdout
pb.inheritIO();                                 // usar stdin/stdout/stderr del proceso padre

Process p = pb.start();
int codigoRetorno = p.waitFor();                // espera a que termine
```

### Leer la salida del proceso

```java
Process p = new ProcessBuilder("ping", "google.com").start();

try (BufferedReader br = new BufferedReader(
        new InputStreamReader(p.getInputStream()))) {
    String linea;
    while ((linea = br.readLine()) != null) {
        System.out.println(linea);
    }
}
p.waitFor();
```

### Variables de entorno

```java
ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "echo", "%MI_VAR%");
pb.environment().put("MI_VAR", "hola");
// La variable estara disponible en el proceso hijo
```

---

## Ejercicios

### Ejemplos sueltos (Ej00 - Ej20)

Son archivos `.java` con un solo `main()` — cada uno demuestra un concepto concreto.

| Archivo | Concepto |
|---------|---------|
| `Ej00Ejemplos.java` | Introduccion general a ProcessBuilder |
| `Ej01MostrarDirectorioActual.java` | Ejecutar `pwd`/`cd` y leer la salida |
| `Ej011MostrarLinea.java` | Leer linea a linea del proceso |
| `Ej02SaveSistemDate.java` | Guardar la fecha del sistema en un fichero |
| `Ej03LogErrores.java` | Redirigir `stderr` a un fichero de log |
| `Ej04InsertCommand.java` | Ejecutar un comando introducido por el usuario |
| `Ej05VarEntorno.java` | Leer y establecer variables de entorno del proceso |
| `Ej06CheckConexion.java` | Comprobar conectividad con `ping` |
| `Ej07LlamadaArchivo.java` | Ejecutar un script (`.bat`/`.sh`) externo |
| `Ej08ProgSimultaneos.java` | Lanzar varios procesos en paralelo |
| `Ej09MultipleProcesos.java` | Gestionar multiples procesos con lista |
| `Ej10Hilos.java` | Combinar hilos con procesos |
| `Ej10SaveErrors.java` | Guardar errores de un proceso en fichero |
| `Ej12ComandoSegunSO.java` | Detectar SO y adaptar el comando (`os.name`) |
| `Ej13ContarSalida.java` | Contar lineas de la salida de un proceso |
| `Ej14EjecutarComando.java` | Menu interactivo de comandos |
| `Ej15CopiaSeg.java` | Copia de seguridad ejecutando un comando del SO |
| `Ej16ProcesoConVariable.java` | Pasar variables al proceso hijo |
| `Ej17RepetirEjecucion.java` | Ejecutar el mismo proceso varias veces |
| `Ej18EspOtroProceso.java` | `waitFor()` y codigo de retorno |
| `Ej19ProcEjec.java` | Procesos en ejecucion del sistema |
| `Ej20MenuInteractivo.java` | Menu completo con opciones de proceso |

### Ejercicios estructurados (con clases separadas)

| Carpeta | Descripcion |
|---------|------------|
| `ejercicio01VariablesEntorno/` | Variables de entorno disponibles en el proceso Java |
| `ejercicio02DirCommand/` | Ejecutar `dir` (Windows) y leer resultado |
| `ejercicio03CMD/` | Comandos interactivos via `cmd.exe` |
| `ejercicio04NotePad/` | Abrir `notepad.exe` (aplicacion grafica) |
| `ejercicio05InheritIO/` | `inheritIO()` — el proceso hijo comparte la consola |
| `ejercicio06RedirigirSalida/` | Redirigir la salida a un fichero |
| `ejercicio07GestorProcesos/` | `GestorProcesos`: encapsula el ciclo de vida de varios procesos |
| `ejercicio08CodigoRetorno/` | Interpretar el codigo de retorno (`exitValue()`) |

---

## Notas de compatibilidad SO

Muchos ejercicios usan comandos especificos de Windows (`cmd`, `dir`, `notepad`). Para ejecutarlos en Linux/Mac, adaptar los comandos:

```java
// Detectar SO
boolean esWindows = System.getProperty("os.name").toLowerCase().contains("win");
String[] comando = esWindows
    ? new String[]{"cmd", "/c", "dir"}
    : new String[]{"ls", "-la"};

ProcessBuilder pb = new ProcessBuilder(comando);
```

---

## Como ejecutar

```bash
javac -d out ProcessProcessBuilder/Ej01MostrarDirectorioActual.java
java -cp out Ej01MostrarDirectorioActual
```

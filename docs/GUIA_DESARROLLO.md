# Guia de Desarrollo — Java2 2ºDAM
> Estado del repositorio, propuesta de temario, bugs pendientes y hoja de ruta

---

## Indice de esta guia

1. [Estado actual del repositorio](#1-estado-actual-del-repositorio)
2. [Bugs y errores criticos pendientes](#2-bugs-y-errores-criticos-pendientes)
3. [Propuesta de indice y orden del temario](#3-propuesta-de-indice-y-orden-del-temario)
4. [Temas que faltan por desarrollar](#4-temas-que-faltan-por-desarrollar)
5. [Mejoras propuestas por tema](#5-mejoras-propuestas-por-tema)
6. [Problemas estructurales del repo](#6-problemas-estructurales-del-repo)
7. [Convenios de codigo que hay que aplicar](#7-convenios-de-codigo-que-hay-que-aplicar)
8. [Hoja de ruta para completar el temario](#8-hoja-de-ruta-para-completar-el-temario)

---

## 1. Estado actual del repositorio

### Que hay y en que estado esta

| Carpeta | Estado | Comentario |
|---------|--------|-----------|
| `Hilos/` | ✅ Funcional con issues | 2 bloques de ejercicios, buen volumen. Issues menores |
| `Sincronizacion/` | ✅ Funcional con issues | Comentarios desactualizados en Restaurante. Excelente teoria en Main.java |
| `Concurrencia/` | ⚠️ Incompleto | `Productor.java` esta VACIO. El ejercicio no puede ejecutarse |
| `AlgoritmosOrdenacion/` | ⚠️ Bug en formulas | `retorno` calculado mal en SRTF y SJF cuando llegada != 0 |
| `ProcessProcessBuilder/` | ✅ Completo | 20+ ejemplos sueltos + 8 ejercicios estructurados. Solapamiento con Tema19 |
| `AccesoDatos/` | ✅ Funcional | Buena base. Falta JSON real y JDBC |
| `Swing/` | ⚠️ Funcional con bug de layout | `usandoJLabel()` se añade con BorderLayout y luego se anula con null layout |
| `Tema17ProgramacionFuncional/` | ✅ El mejor tema del repo | Muy completo. Nomenclatura mixta (carpetas/sueltos) |
| `Tema18ProgramacionRed/` | ⚠️ Esqueleto | Solo URL y esquema de servidor. Faltan ejercicios de Socket cliente-servidor |
| `Tema19ContructorProcesos/` | ✅ Funcional | Solapamiento con `ProcessProcessBuilder/` — misma materia |

### Resumen por numeros

- **Temas con codigo funcional y completo:** 4 (Hilos, Sincronizacion, ProcessProcessBuilder, Tema17)
- **Temas con bugs criticos:** 3 (Concurrencia, AlgoritmosOrdenacion, Swing)
- **Temas incompletos o en esqueleto:** 3 (Tema18, Concurrencia, AccesoDatos)
- **Temas completamente ausentes:** 5+ (JDBC, Testing, Logging, Serialización, Generics avanzado)
- **READMEs:** Creados en el commit anterior para todos los temas

---

## 2. Bugs y errores criticos pendientes

### BUG CRITICO — `Concurrencia/productorConsumidor/Productor.java` esta VACIO

```java
// Estado actual — no hace nada
package productorConsumidor;

public class Productor {

}
```

El ejercicio Productor-Consumidor **no puede ejecutarse** porque el `Productor` no tiene implementacion.
Tambien falta el metodo `producir()` en `Buffer.java` — solo existe `consumir()`.

**Que falta implementar:**
```java
// Buffer.java — falta este metodo
public synchronized void producir(char c) {
    while (estaLlena) {
        try { wait(); } catch (InterruptedException e) { throw new RuntimeException(e); }
    }
    buffer[siguiente] = c;
    siguiente++;
    if (siguiente == buffer.length) estaLlena = true;
    estaVacia = false;
    notifyAll();
}

// Productor.java — falta toda la implementacion
public class Productor implements Runnable {
    private Buffer buffer;

    public Productor(Buffer buffer) { this.buffer = buffer; }

    @Override
    public void run() {
        for (char c = 'A'; c <= 'Z'; c++) {
            buffer.producir(c);
            System.out.println("Producido: " + c);
        }
    }
}
```

---

### BUG CRITICO — Formula de `retorno` incorrecta en SJF y SRTF

En `SJF/SJF.java` y `SRTF/SRTF.java`:

```java
// INCORRECTO — retorno = finalizacion (solo valido si todos llegan en t=0)
retorno[idx] = finalizacion[idx];

// CORRECTO — retorno = finalizacion - llegada
retorno[idx] = finalizacion[idx] - llegada[idx];
```

La formula de espera SI es correcta en ambos:
```java
espera[idx] = finalizacion[idx] - rafaga[idx] - llegada[idx];
// que equivale a: espera = retorno - rafaga (con el retorno correcto)
```

**Impacto:** Si los procesos no llegan todos en t=0 (caso general), la columna Retorno en la tabla de resultados muestra el instante de finalizacion en lugar del tiempo de respuesta real del proceso. Para un alumno que este validando sus calculos manuales contra el programa, los numeros no cuadran.

**Nota FIFO:** FIFO.java tiene el mismo problema pero con un comentario explicativo intencional. Si se va a corregir, corregirlo en los tres.

---

### BUG — `Swing/Ej01Saludo.java` — null layout anula el JLabel añadido antes

```java
// Problema: el JLabel se añade CON posicionamiento BorderLayout
ventana.add(usandoJLabel(), BorderLayout.CENTER);

// Luego se cambia a null layout — el JLabel anterior no tiene bounds definidos
// y no se mostrara correctamente (quedara en posicion 0,0 con tamaño 0x0)
ventana.setLayout(null);
```

**Solucion:** Si se usa null layout, todos los componentes necesitan `setBounds()`. Si se usa BorderLayout, no poner `setLayout(null)`. Hay que elegir una estrategia y ser consistente.

---

### BUG MENOR — `Sincronizacion/Restaurante/Main.java` — comentarios desactualizados

```java
// El comentario dice "Crear 3 camareros" pero el bucle crea 10
for (int i = 1; i <= 10; i++) {  // <-- son 10, no 3
    Thread camarero = new Thread(new Camarero(cocina, i));
    camarero.start();
}

// El comentario dice "Crear 2 cocineros" pero el bucle crea 5
for (int i = 1; i <= 5; i++) {   // <-- son 5, no 2
    Thread cocinero = new Thread(new Cocinero(i, cocina));
    cocinero.start();
}
```

---

### BUG MENOR — `Sincronizacion/Ej06Filosofos/Filosofo.java` — texto y typo

```java
// ACTUAL — falta espacio y typo "pensado" en lugar de "pensando"
System.out.println("Filosofo " + comensal + "esta pensado.");
System.out.println("Filosofo " + comensal + "esta comiendo.");

// CORRECTO
System.out.println("Filosofo " + comensal + " esta pensando.");
System.out.println("Filosofo " + comensal + " esta comiendo.");
```

---

### BUG MENOR — `Tema19ContructorProcesos/src/main/java/ejercicio08.java` — nombre de clase incorrecto

El archivo se llama `ejercicio08.java` (minuscula) pero la clase publica debe llamarse como el archivo.
En Java las clases publicas deben seguir `PascalCase`: `Ejercicio08.java`.
Esto puede causar errores en algunos compiladores y herramientas.

---

### AVISO — Emojis en salida de consola (`Tema17ProgramacionFuncional/`)

Archivos como `Ejercicio12Repaso/MainConMap/Main.java` usan emojis en `System.out.printf`:
```java
System.out.println("\n🟢 Producto más barato: " + p);
System.out.printf("💰 Total Frutas: %.2f €\n", totalFrutas);
```

En terminales de Windows sin configuracion UTF-8 correcta (que es el caso por defecto), los emojis se muestran como `?` o caracteres corruptos. Solución: configurar `chcp 65001` antes de ejecutar, o eliminar los emojis del codigo de produccion.

---

### AVISO — `Hilos/Ejercicio01Thread/Main.java` — pseudo-codigo dentro de comentario

El comentario de este archivo contiene pseudo-codigo no-Java que no es ejecutable:
```java
/*
    .run()
    .start()
    .sleep(long milis) -> metodo stático de thread...
    .join() -> Sirve para que un hilo Espere...
*/
```

No es un error de compilacion (esta comentado) pero es confuso para un alumno que intente entender la estructura. Deberia quedar como comentario explicativo en prosa o moverse al README.

---

## 3. Propuesta de indice y orden del temario

### Orden actual (problemas)

Los temas estan mezclados: carpetas con prefijo `Tema17`, `Tema18`, `Tema19` y otras sin prefijo. El orden logico de aprendizaje no es evidente navegando el repo.

### Orden propuesto para 2ºDAM

Este orden va de mas simple a mas complejo y respeta las dependencias entre temas:

```
00-fundamentos-previos/          (NUEVO - prerequisitos no cubiertos)
├── colecciones/                 ArrayList, HashMap, LinkedList, TreeMap
├── generics/                    T, K, V, wildcards
├── excepciones-personalizadas/  CustomException, jerarquia de excepciones
└── enum/                        Enumeraciones

01-programacion-funcional/       (= Tema17ProgramacionFuncional/ actual)
├── lambdas/                     interfaces funcionales, sintaxis
├── streams/                     pipeline, operaciones intermedias/terminales
├── optionals/                   evitar null
└── referencias-metodos/         ::

02-acceso-datos/                 (= AccesoDatos/ actual + JDBC)
├── texto-plano/                 BufferedReader/Writer
├── csv/                         split, parseo, CsvUtils
├── json/                        Jackson o Gson
├── serializacion/               ObjectOutputStream/InputStream
└── jdbc/                        Connection, Statement, PreparedStatement, ResultSet

03-interfaces-graficas/          (= Swing/ actual)
├── componentes/                 JFrame, JPanel, JLabel, JButton, JTextField
├── layouts/                     BorderLayout, GridLayout, FlowLayout
├── eventos/                     ActionListener, KeyListener, MouseListener
└── proyecto/                    app completa con datos

04-hilos/                        (= Hilos/ actual)
├── thread-runnable/             extends Thread, implements Runnable
├── ciclo-vida/                  estados, metodos basicos
├── sleep-join/                  coordinacion basica
├── prioridades/                 setPriority, isAlive
└── executor-service/            pools de hilos, Callable, Future

05-sincronizacion/               (= Sincronizacion/ actual)
├── zona-critica/                race conditions, consecuencias
├── synchronized/                metodo, bloque, static
├── reentrant-lock/              Lock, tryLock, ReadWriteLock
└── problemas-clasicos/          deadlock, filosofos, banco

06-concurrencia/                 (= Concurrencia/ actual - COMPLETAR)
├── productor-consumidor/        wait/notify/notifyAll, buffer
├── monitor/                     concepto y uso
└── patrones-avanzados/          semaforos, barreras, latch

07-procesos/                     (unificar ProcessProcessBuilder/ + Tema19/)
├── process-runtime/             Runtime.exec basico
├── process-builder/             configuracion completa
├── io-procesos/                 stdin, stdout, stderr del proceso hijo
└── procesos-avanzados/          paralelo, gestion, codigo retorno

08-planificacion-procesos/       (= AlgoritmosOrdenacion/ actual)
├── conceptos/                   llegada, rafaga, retorno, espera
├── fifo/                        implementacion y ejemplo
├── sjf/                         implementacion + correccion formula
├── srtf/                        implementacion + correccion formula
└── round-robin/                 implementacion con Deque

09-programacion-red/             (= Tema18/ actual - AMPLIAR)
├── url-http/                    clase URL, HttpURLConnection
├── sockets-tcp/                 Socket, ServerSocket, protocolo
├── cliente-servidor/            arquitectura, hilos por cliente
└── api-rest-cliente/            consumir una API JSON con HTTP

10-testing/                      (NUEVO - fundamental para DAM)
├── junit5/                      @Test, @BeforeEach, Assertions
├── mocking/                     Mockito basico
└── tdd/                         ciclo red-green-refactor

11-logging/                      (NUEVO)
├── java-logging/                java.util.logging
└── slf4j-logback/               la opcion profesional
```

### Por que este orden

- **00 Fundamentos:** muchos alumnos llegan sin dominar colecciones y generics. Si no los entienden, los Streams de Tema17 son magia negra.
- **01 Funcional primero:** las lambdas y streams se usan en todos los temas siguientes (AccesoDatos, Hilos, Red). Tenerlos claros facilita todo lo demas.
- **04-06 Hilos → Sincronizacion → Concurrencia:** orden natural de complejidad. No se puede entender sincronizacion sin saber que son los hilos, ni el patron Productor-Consumidor sin sincronizacion.
- **07 Procesos despues de Hilos:** `ProcessBuilder` con hilos tiene mas sentido una vez que se entiende la concurrencia.
- **10-11 Testing y Logging al final:** no son prerequisito pero son imprescindibles en proyectos reales.

---

## 4. Temas que faltan por desarrollar

### Critico — JDBC (Acceso a bases de datos)

**Por que es critico:** Es uno de los modulos centrales de 2ºDAM "Acceso a Datos". Hay un PDF de JDBC en `AccesoDatos/docs/` que indica que estaba planificado pero nunca se implemento.

**Que incluir:**
```java
// Conexion basica (con MySQL/MariaDB)
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mi_bd", "usuario", "password");

// PreparedStatement (SIEMPRE usar esto, no Statement, para evitar SQL injection)
PreparedStatement ps = conn.prepareStatement(
    "SELECT * FROM clientes WHERE id = ?");
ps.setInt(1, 5);
ResultSet rs = ps.executeQuery();

while (rs.next()) {
    System.out.println(rs.getString("nombre"));
}

// CRUD completo, patron DAO con JDBC, Connection Pool basico
```

**Ejercicios propuestos:**
1. Conectar a BD y hacer SELECT
2. CRUD completo de una entidad (Cliente, Producto...)
3. Patron DAO: separar logica de BD de la logica de negocio
4. Transacciones: `conn.setAutoCommit(false)`, `commit()`, `rollback()`
5. Connection Pool con HikariCP

---

### Importante — Serializacion de objetos

**Hay un archivo `AccesoDatos/data/datos.dat`** que sugiere que la serializacion estaba planificada pero nunca implementada.

```java
// Serializar (guardar objeto en fichero binario)
try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("datos.dat"))) {
    oos.writeObject(miObjeto);
}

// Deserializar (leer objeto desde fichero)
try (ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream("datos.dat"))) {
    MiClase obj = (MiClase) ois.readObject();
}
```

**Ejercicios propuestos:**
1. Serializar y deserializar un objeto simple
2. Lista de objetos en un fichero .dat
3. Diferencia entre `Serializable` y `Externalizable`
4. `serialVersionUID` y compatibilidad de versiones

---

### Importante — Sockets TCP en Tema18

`Tema18ProgramacionRed/` solo tiene `Conexion.java` (clase URL) y el esqueleto de servidores.
Faltan ejercicios funcionales de cliente-servidor con `Socket` y `ServerSocket`.

**Ejercicios propuestos:**
1. Servidor eco: recibe un mensaje y lo devuelve en mayusculas
2. Servidor de hora: el cliente pide la hora, el servidor responde
3. Servidor concurrente: un hilo por cliente con `ExecutorService`
4. Chat simple: varios clientes se conectan a un servidor central
5. Protocolo de aplicacion custom: definir un protocolo de texto tipo HTTP basico

---

### Importante — Testing con JUnit 5

**No hay ni un test en todo el repo.** En 2ºDAM se exige testing. JUnit 5 es el estandar.

**Estructura propuesta:**
```
10-testing/
├── pom.xml                          <- JUnit 5 + Mockito como dependencias
└── src/
    ├── main/java/
    │   ├── calculadora/Calculadora.java
    │   └── banco/CuentaBancaria.java  <- las mismas clases del tema de sincronizacion
    └── test/java/
        ├── calculadora/CalculadoraTest.java
        └── banco/CuentaBancariaTest.java
```

**Ejercicios propuestos:**
1. Tests unitarios de la clase `Contador` de Sincronizacion
2. Tests de las operaciones CRUD de AccesoDatos
3. Tests con `@ParameterizedTest` para los algoritmos de planificacion
4. Mock de la conexion JDBC con Mockito

---

### Util — Logging profesional

`System.out.println` no es logging. En proyectos reales se usa un framework de logging.

**Que cubrir:**
```java
// java.util.logging (sin dependencias)
private static final Logger LOG = Logger.getLogger(MiClase.class.getName());
LOG.info("Mensaje informativo");
LOG.warning("Advertencia");
LOG.severe("Error grave");

// SLF4J + Logback (la opcion profesional, requiere Maven/Gradle)
private static final Logger log = LoggerFactory.getLogger(MiClase.class);
log.info("Proceso iniciado con {} parametros", n);
log.error("Error al conectar con la BD: {}", e.getMessage());
```

---

### Util — Colecciones avanzadas y Generics

Muchos alumnos llegan sin dominar estas estructuras. Son prerequisito real para los streams.

**Que cubrir:**
- `ArrayList` vs `LinkedList`: cuando usar cada una
- `HashMap` vs `TreeMap` vs `LinkedHashMap`
- `HashSet`, `TreeSet`: operaciones de conjunto
- `Collections` utility class: `sort()`, `reverse()`, `shuffle()`, `frequency()`
- Generics: `<T>`, `<? extends T>`, `<? super T>`

---

### Util — Patrones de diseño basicos

Para 2ºDAM son exigibles: Singleton, Factory, DAO, Observer.

**Ya hay un ejemplo de DAO** en AccesoDatos (ejercicio02Bonana). Falta documentarlo como patron y anadir los demas.

---

## 5. Mejoras propuestas por tema

### Hilos

| Mejora | Descripcion |
|--------|------------|
| Añadir `ExecutorService` como bloque C | Los bloques A y B usan `Thread` raw. Falta un bloque C con `newFixedThreadPool`, `newCachedThreadPool`, `ScheduledExecutorService` |
| `CompletableFuture` | El uso moderno de concurrencia en Java. Basico para backend y APIs |
| `Callable` + `Future` | Ejercicio especifico. Solo se menciona en teoria, no hay ejercicio |
| Diagrama de estados en README | Un diagrama ASCII del ciclo de vida NEW → RUNNABLE → RUNNING → BLOCKED → TERMINATED |
| Ejercicio de `AtomicInteger` | `ejercicio2110` ya lo tiene pero sin explicar por que es mejor que `volatile int` |

### Sincronizacion

| Mejora | Descripcion |
|--------|------------|
| Ejercicio deadlock intencional | Mostrar el deadlock con dos hilos y dos locks — luego solucionarlo con orden de adquisicion |
| `ReadWriteLock` | `ReentrantReadWriteLock`: multiples lectores, un escritor — muy util para caches |
| `Semaphore` de Java | Ya hay uno manual en Hilos/ejercicio219 pero el de `java.util.concurrent.Semaphore` es el real |
| `CountDownLatch` | Esperar a que N hilos terminen antes de continuar |
| `CyclicBarrier` | Punto de sincronizacion donde todos los hilos esperan |

### Concurrencia

| Mejora | Descripcion |
|--------|------------|
| **Completar `Productor.java`** | CRITICO — sin esto el ejercicio no funciona |
| **Anadir `producir()` a `Buffer.java`** | CRITICO — solo existe `consumir()` |
| Ejercicio con `BlockingQueue` | `java.util.concurrent.BlockingQueue` es la version moderna de Productor-Consumidor — elimina el `wait/notify` manual |
| Ejercicio con `LinkedBlockingQueue` | Ver como `ArrayBlockingQueue.put()` y `take()` hacen lo mismo que el buffer manual |

### AlgoritmosOrdenacion

| Mejora | Descripcion |
|--------|------------|
| **Corregir formula retorno** | `retorno = finalizacion - llegada` en SJF y SRTF |
| Diagrama de Gantt en FIFO y SJF | RR y SRTF ya tienen Gantt. FIFO y SJF deberian tambien |
| Clase base `Proceso` | Refactorizar para evitar duplicacion: los 4 algoritmos tienen los mismos arrays. Una clase `Proceso` con llegada, rafaga, finalizacion, retorno, espera |
| Test de los algoritmos | Verificar con ejemplos conocidos del libro |
| Añadir `Priority Queue` | HRRN (Highest Response Ratio Next) si se quiere completar el temario de planificacion |

### Acceso a Datos

| Mejora | Descripcion |
|--------|------------|
| **Implementar JDBC** | CRITICO para el modulo — hay PDF pero no hay codigo |
| Ejercicio JSON con Jackson | `biblioteca.json` existe pero no hay codigo que lo lea |
| Patron Repository | Evolucion natural del patron DAO para JDBC |
| Ejercicio con `Files.walk()` | Recorrer un directorio y procesar todos los .txt o .csv |
| Fichero de propiedades | Leer configuracion desde `.properties` — muy comun en proyectos reales |

### Swing

| Mejora | Descripcion |
|--------|------------|
| **Corregir bug BorderLayout + null** | En `Ej01Saludo.java` linea 31-32 |
| Mover Swing al EDT | `SwingUtilities.invokeLater(...)` en todos los `main()` |
| `JTable` | Para mostrar datos tabulares — muy pedido en examenes |
| `JDialog` | Ventana de dialogo personalizada (alternativa a `JOptionPane`) |
| `JFileChooser` | Seleccionar fichero desde la UI — conecta con AccesoDatos |
| Ejercicio CRUD completo en Swing | Tabla de productos con botones Añadir, Editar, Borrar — integra Swing + AccesoDatos |

### Programacion Funcional

| Mejora | Descripcion |
|--------|------------|
| Unificar nomenclatura | Algunos ejercicios estan en carpeta `Ejercicio01/` y otros como `Ejercicio01Password.java` sueltos. Decidir un convenio |
| Añadir `Collectors.joining()` | Muy comun en examenes — unir strings de un stream |
| Añadir `flatMap()` | Stream de streams — es donde muchos alumnos se atascan |
| Anadir `reduce()` | Operacion terminal fundamental que falta en los ejemplos |
| Ejercicio `Map.Entry` streams | Iterar sobre un mapa con streams — Ejercicio12 lo roza pero merece su propio ejercicio |

### Programacion de Red

| Mejora | Descripcion |
|--------|------------|
| **Servidor eco funcional** | El esqueleto existe (`Servidor.java`) pero no hay cliente para probarlo |
| Ejercicio de chat multiusuario | El ejercicio mas completo del tema — integra hilos + sockets |
| Consumir API REST | `HttpURLConnection` o `HttpClient` (Java 11+) para hacer GET a una API real |
| Parsear respuesta JSON | Combinado con AccesoDatos (Jackson/Gson) |

---

## 6. Problemas estructurales del repo

### Problema 1 — Solapamiento `ProcessProcessBuilder/` vs `Tema19ContructorProcesos/`

Ambas carpetas cubren `ProcessBuilder`. Tienen ejercicios similares y no queda claro la diferencia.

**Propuesta:** Unificarlas en `07-procesos/`:
- `ProcessProcessBuilder/` → ejemplos didacticos sueltos (Ej00 a Ej20)
- `Tema19ContructorProcesos/` → proyecto Maven estructurado

**Alternativa conservadora:** Renombrar `ProcessProcessBuilder/` a `07a-procesos-ejemplos/` y `Tema19ContructorProcesos/` a `07b-procesos-maven/` y dejar una nota en los READMEs explicando la diferencia.

---

### Problema 2 — Inconsistencia en prefijos de carpeta

- Con prefijo: `Tema17ProgramacionFuncional/`, `Tema18ProgramacionRed/`, `Tema19ContructorProcesos/`
- Sin prefijo: `Hilos/`, `Sincronizacion/`, `Concurrencia/`, `AlgoritmosOrdenacion/`, `Swing/`, `AccesoDatos/`

**Propuesta:** Adoptar un convenio unico. Lo mas limpio para un repo educativo:

```
01-hilos/
02-sincronizacion/
03-concurrencia/
04-programacion-funcional/
05-acceso-datos/
06-swing/
07-procesos/
08-planificacion/
09-programacion-red/
```

Renombrar preservando los commits con `git mv` para no perder historial.

---

### Problema 3 — Dos series de ejercicios en Hilos sin conexion clara

El tema de Hilos tiene:
- **Serie A:** `Ejercicio01Thread/`, `Ejercicio011RunStart/`, ..., `Ejercicio08TresHilosOrden/`
- **Serie B:** `ejercicio131/`, `ejercicio142/`, ..., `ejercicio2212/`

No hay ningun archivo que explique la relacion entre ambas series. La Serie B parece ejercicios de un PDF o libro (por los numeros 13.1, 14.2...) pero el PDF (`Ejercicios Hilos.pdf`) esta en la carpeta pero no se puede leer directamente.

**Propuesta:** Añadir en `Hilos/README.md` (ya existe) la tabla con la equivalencia explicita. Ademas, crear una subcarpeta `serie-A/` y `serie-B/` o al menos separar visualmente los dos grupos.

---

### Problema 4 — `Concurrencia/Teoría/Main.java` tiene acento en el nombre de paquete

```java
package Teoría;  // La "í" con acento puede causar problemas
```

Algunos sistemas de ficheros (especialmente Windows con codificaciones antiguas) y algunas herramientas Java tienen problemas con nombres de paquete que contienen caracteres no-ASCII. Deberia ser `package Teoria;`.

---

### Problema 5 — Falta un proyecto integrador

El curriculum de 2ºDAM generalmente incluye un proyecto final que integra varios modulos. No hay ningun proyecto de este tipo en el repo.

**Propuesta de proyecto integrador:**
```
proyectos/
└── gestion-empleados/
    ├── README.md
    ├── pom.xml
    └── src/
        ├── main/java/
        │   ├── modelo/Empleado.java
        │   ├── dao/EmpleadoDAO.java          <- JDBC
        │   ├── servicio/EmpleadoService.java  <- logica de negocio
        │   ├── ui/MainWindow.java             <- Swing
        │   └── Main.java
        └── test/java/
            └── EmpleadoServiceTest.java       <- JUnit 5
```

---

## 7. Convenios de codigo que hay que aplicar

Estos convenios deben ser consistentes en TODOS los archivos del repo:

### Naming

| Elemento | Convenio | Ejemplo |
|---------|----------|---------|
| Clase | PascalCase | `CuentaBancaria` |
| Metodo | camelCase | `obtenerSaldo()` |
| Variable | camelCase | `tiempoRestante` |
| Constante | UPPER_SNAKE_CASE | `MAX_INTENTOS` |
| Paquete | lowercase | `ejercicio01`, `productorconsumidor` |
| Archivo | Igual que la clase publica | `CuentaBancaria.java` |

**Violaciones actuales:**
- `ejercicio08.java` en Tema19 (minuscula — deberia ser `Ejercicio08.java`)
- Paquetes con mayusculas: `package Fifo`, `package RR`, `package Ej01Contador` — deben ser lowercase
- `package Teoría` — acento no permitido en paquetes

### Estructura de clases

```java
// Orden recomendado dentro de una clase:
public class MiClase {
    // 1. Constantes (static final)
    private static final int MAX = 10;

    // 2. Campos de instancia (privados)
    private int valor;

    // 3. Constructores
    public MiClase(int valor) { this.valor = valor; }

    // 4. Metodos publicos (getters, setters, metodos de negocio)
    public int getValor() { return valor; }

    // 5. Metodos privados (auxiliares)
    private void calcular() { }
}
```

### Manejo de excepciones

```java
// MAL — nunca hacer esto
} catch (Exception e) { }  // silenciar excepciones

// MAL — e.printStackTrace() en produccion no es suficiente
} catch (IOException e) { e.printStackTrace(); }

// BIEN — al menos logear o relanzar
} catch (IOException e) {
    System.err.println("Error al leer fichero: " + e.getMessage());
    // En proyectos reales: logger.error("...", e);
}
```

### try-with-resources — SIEMPRE para I/O y conexiones

```java
// BIEN — se cierra automaticamente aunque haya excepcion
try (BufferedReader br = Files.newBufferedReader(ruta)) {
    // usar br
}

// MAL — si hay excepcion en la lectura, close() nunca se llama
BufferedReader br = Files.newBufferedReader(ruta);
// usar br
br.close();  // nunca llega aqui si hay excepcion antes
```

---

## 8. Hoja de ruta para completar el temario

### Prioridad 1 — Bugs criticos (hacer antes de usar el repo con alumnos)

- [ ] Completar `Concurrencia/productorConsumidor/Productor.java`
- [ ] Anadir metodo `producir()` a `Concurrencia/productorConsumidor/Buffer.java`
- [ ] Corregir formula `retorno` en `AlgoritmosOrdenacion/SJF/SJF.java`
- [ ] Corregir formula `retorno` en `AlgoritmosOrdenacion/SRTF/SRTF.java`
- [ ] Corregir bug null layout en `Swing/Ej01Saludo.java`
- [ ] Corregir comentarios en `Sincronizacion/Restaurante/Main.java`
- [ ] Corregir typos en `Sincronizacion/Ej06Filosofos/Filosofo.java`
- [ ] Renombrar `ejercicio08.java` a `Ejercicio08.java` en Tema19

### Prioridad 2 — Temas incompletos criticos

- [ ] Implementar `AccesoDatos/ejercicio04json/` con Jackson o Gson
- [ ] Implementar `AccesoDatos/ejercicio05jdbc/` con JDBC + MySQL/MariaDB
- [ ] Implementar `AccesoDatos/ejercicio06serializacion/` con ObjectOutputStream
- [ ] Completar `Tema18ProgramacionRed/` con ejercicios de Socket cliente-servidor funcionales
- [ ] Añadir bloque de `ExecutorService`, `Callable`, `Future` a `Hilos/`

### Prioridad 3 — Temas nuevos importantes

- [ ] Crear `10-testing/` con JUnit 5 — estructura Maven + tests de las clases existentes
- [ ] Crear `00-fundamentos/colecciones/` y `00-fundamentos/generics/`
- [ ] Crear `11-logging/` con java.util.logging basico y ejemplo de SLF4J
- [ ] Anadir `BlockingQueue` como alternativa moderna a Productor-Consumidor manual

### Prioridad 4 — Mejoras de calidad

- [ ] Unificar nomenclatura de carpetas (prefijos numericos o sin ellos, consistente)
- [ ] Mover `SwingUtilities.invokeLater()` a todos los main() de Swing
- [ ] Corregir nombres de paquetes a lowercase en AlgoritmosOrdenacion, Sincronizacion
- [ ] Añadir diagrama de Gantt a `AlgoritmosOrdenacion/Fifo/` y `AlgoritmosOrdenacion/SJF/`
- [ ] Añadir clase base `Proceso` para evitar duplicacion en los 4 algoritmos
- [ ] Resolver acentos en `package Teoría` de Concurrencia

### Prioridad 5 — Proyecto integrador

- [ ] Disenar y crear `proyectos/gestion-empleados/` que integre Swing + JDBC + testing
- [ ] Documentar el proyecto como ejercicio final del modulo

---

*Documento generado el 2026-03-21. Actualizar conforme se vayan completando los puntos.*

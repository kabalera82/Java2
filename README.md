# Java 2 — Apuntes 2ºDAM

> Repositorio de teoría, apuntes y ejercicios de Java para segundo curso de Desarrollo de Aplicaciones Multiplataforma (DAM). Cada carpeta es un tema independiente con su propio README, ejemplos funcionales y ejercicios comentados.

---

## Indice

1. [Estado actual del repositorio](#1-estado-actual-del-repositorio)
2. [Temario actual y orden propuesto](#2-temario-actual-y-orden-propuesto)
3. [Bugs y errores pendientes](#3-bugs-y-errores-pendientes)
4. [Temas que faltan por desarrollar](#4-temas-que-faltan-por-desarrollar)
5. [Mejoras propuestas por tema](#5-mejoras-propuestas-por-tema)
6. [Problemas estructurales del repo](#6-problemas-estructurales-del-repo)
7. [Convenios de codigo](#7-convenios-de-codigo)
8. [Hoja de ruta](#8-hoja-de-ruta)
9. [Requisitos y compilacion](#9-requisitos-y-compilacion)

---

## 1. Estado actual del repositorio

| Carpeta | Estado | Comentario |
|---------|--------|-----------|
| `Hilos/` | ✅ Funcional con issues | 2 bloques de ejercicios, buen volumen. Issues menores |
| `Sincronizacion/` | ✅ Funcional con issues | Comentarios desactualizados en Restaurante. Excelente teoria en Main.java |
| `Concurrencia/` | ⚠️ Incompleto | `Productor.java` esta VACIO — el ejercicio no puede ejecutarse |
| `AlgoritmosOrdenacion/` | ⚠️ Bug en formulas | `retorno` calculado mal en SJF y SRTF cuando llegada != 0 |
| `ProcessProcessBuilder/` | ✅ Completo | 20+ ejemplos sueltos + 8 ejercicios. Solapamiento con Tema19 |
| `AccesoDatos/` | ✅ Funcional | Buena base. Falta JSON real y JDBC |
| `Swing/` | ⚠️ Bug de layout | `JLabel` añadido con BorderLayout, luego anulado con null layout |
| `Tema17ProgramacionFuncional/` | ✅ El mejor tema del repo | Muy completo. Nomenclatura mixta (carpetas/sueltos) |
| `Tema18ProgramacionRed/` | ⚠️ Esqueleto | Solo URL y esquema de servidor. Faltan ejercicios de Socket real |
| `Tema19ContructorProcesos/` | ✅ Funcional | Solapamiento con `ProcessProcessBuilder/` — misma materia |

**Resumen:**
- Temas funcionales y completos: **4** (Hilos, Sincronizacion, ProcessProcessBuilder, Tema17)
- Temas con bugs criticos: **3** (Concurrencia, AlgoritmosOrdenacion, Swing)
- Temas en esqueleto: **3** (Tema18, Concurrencia, AccesoDatos)
- Temas completamente ausentes: **5+** (JDBC, Testing, Logging, Serializacion, Generics)

---

## 2. Temario actual y orden propuesto

### Temario actual

| # | Carpeta | Contenido |
|---|---------|-----------|
| 01 | [Hilos](./Hilos/README.md) | `Thread`, `Runnable`, `sleep`, `join`, `setPriority`, `isAlive`, ciclo de vida |
| 02 | [Sincronizacion](./Sincronizacion/README.md) | `synchronized`, zona critica, `ReentrantLock`, deadlocks, filosofos |
| 03 | [Concurrencia](./Concurrencia/README.md) | Productor-Consumidor, `wait/notify/notifyAll`, monitor |
| 04 | [AlgoritmosOrdenacion](./AlgoritmosOrdenacion/README.md) | FIFO, SJF, SRTF, Round Robin |
| 05 | [ProcessProcessBuilder](./ProcessProcessBuilder/README.md) | `Process`, `ProcessBuilder`, variables de entorno, redireccion I/O |
| 06 | [AccesoDatos](./AccesoDatos/README.md) | Ficheros de texto, CSV, JSON, patron DAO |
| 07 | [Swing](./Swing/README.md) | `JFrame`, layouts, eventos, componentes |
| 08 | [Tema17 — Prog. Funcional](./Tema17ProgramacionFuncional/README.md) | Lambdas, `Stream`, `Optional`, referencias a metodos |
| 09 | [Tema18 — Prog. de Red](./Tema18ProgramacionRed/README.md) | `URL`, `Socket`, `ServerSocket`, cliente-servidor |
| 10 | [Tema19 — Constructor Procesos](./Tema19ContructorProcesos/README.md) | `ProcessBuilder` avanzado con Maven |

### Orden propuesto (pedagogico — de menor a mayor complejidad)

Los temas tienen dependencias entre si. Este es el orden logico de aprendizaje:

```
00-fundamentos-previos/          ← NUEVO — prerequisitos no cubiertos
├── colecciones/                 ArrayList, HashMap, LinkedList, TreeMap
├── generics/                    T, K, V, wildcards
├── excepciones-personalizadas/  CustomException, jerarquia
└── enum/                        Enumeraciones

01-programacion-funcional/       ← Tema17 actual
├── lambdas/
├── streams/
├── optionals/
└── referencias-metodos/

02-acceso-datos/                 ← AccesoDatos actual + JDBC nuevo
├── texto-plano/
├── csv/
├── json/
├── serializacion/               ← NUEVO
└── jdbc/                        ← NUEVO (critico)

03-interfaces-graficas/          ← Swing actual
├── componentes/
├── layouts/
├── eventos/
└── proyecto-crud/               ← NUEVO — app completa con datos

04-hilos/                        ← Hilos actual
├── thread-runnable/
├── ciclo-vida/
├── sleep-join/
├── prioridades/
└── executor-service/            ← NUEVO — falta bloque con Callable/Future

05-sincronizacion/               ← Sincronizacion actual
├── zona-critica/
├── synchronized/
├── reentrant-lock/
└── problemas-clasicos/

06-concurrencia/                 ← Concurrencia actual — COMPLETAR
├── productor-consumidor/        ← implementar Productor.java
├── monitor/
└── blocking-queue/              ← NUEVO — version moderna

07-procesos/                     ← unificar ProcessProcessBuilder + Tema19
├── process-runtime/
├── process-builder/
├── io-procesos/
└── procesos-avanzados/

08-planificacion-procesos/       ← AlgoritmosOrdenacion actual
├── conceptos/
├── fifo/
├── sjf/                         ← corregir formula retorno
├── srtf/                        ← corregir formula retorno
└── round-robin/

09-programacion-red/             ← Tema18 actual — AMPLIAR
├── url-http/
├── sockets-tcp/                 ← implementar ejercicios funcionales
├── cliente-servidor/
└── api-rest-cliente/

10-testing/                      ← NUEVO — fundamental para DAM
├── junit5/
├── mocking/
└── tdd/

11-logging/                      ← NUEVO
├── java-logging/
└── slf4j-logback/
```

**Por que este orden:**
- **00 Fundamentos:** muchos alumnos llegan sin dominar colecciones y generics. Sin ellos los Streams son magia negra.
- **01 Funcional primero:** lambdas y streams se usan en todos los temas siguientes — mejor tenerlos claros desde el inicio.
- **04→05→06 Hilos → Sincronizacion → Concurrencia:** no se puede entender sincronizacion sin hilos, ni Productor-Consumidor sin sincronizacion.
- **07 Procesos despues de Hilos:** `ProcessBuilder` con hilos tiene mas sentido con la concurrencia clara.
- **10-11 Testing y Logging al final:** no son prerequisito pero son imprescindibles en proyectos reales.

---

## 3. Bugs y errores pendientes

### BUG CRITICO — `Concurrencia/productorConsumidor/Productor.java` esta VACIO

```java
// Estado actual — no hace nada
package productorConsumidor;

public class Productor {

}
```

El ejercicio Productor-Consumidor **no puede ejecutarse**. Tambien falta el metodo `producir()` en `Buffer.java` — solo existe `consumir()`.

**Implementacion pendiente:**

```java
// Buffer.java — anadir este metodo
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

// Productor.java — implementacion completa
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

### BUG CRITICO — Formula `retorno` incorrecta en SJF y SRTF

En `AlgoritmosOrdenacion/SJF/SJF.java` y `SRTF/SRTF.java`:

```java
// INCORRECTO — solo valido si todos los procesos llegan en t=0
retorno[idx] = finalizacion[idx];

// CORRECTO — tiempo de respuesta desde que el proceso llego
retorno[idx] = finalizacion[idx] - llegada[idx];
```

La formula de espera SI es correcta en ambos archivos:
```java
espera[idx] = finalizacion[idx] - rafaga[idx] - llegada[idx];
// equivale a: espera = retorno - rafaga (con el retorno correcto)
```

**Impacto:** Cuando los procesos no llegan todos en t=0 (caso general), la columna Retorno muestra el instante de finalizacion en lugar del tiempo de respuesta real. Los calculos manuales de un alumno no cuadran con la salida del programa.

**Nota FIFO:** `FIFO.java` tiene el mismo calculo con un comentario explicativo intencional. Si se corrige, corregir en los tres archivos.

---

### BUG — `Swing/Ej01Saludo.java` — null layout anula el JLabel

```java
// El JLabel se añade con BorderLayout
ventana.add(usandoJLabel(), BorderLayout.CENTER);

// Luego se cambia a null layout — el JLabel queda en 0,0 con tamanio 0x0
ventana.setLayout(null);
```

**Solucion:** Elegir una estrategia y ser consistente. Si se usa `null` layout, todos los componentes necesitan `setBounds()`. Si se usa `BorderLayout`, no llamar a `setLayout(null)` despues.

---

### BUG MENOR — `Sincronizacion/Restaurante/Main.java` — comentarios desactualizados

```java
// El comentario dice "Crear 3 camareros" pero el bucle crea 10
for (int i = 1; i <= 10; i++) { ... }

// El comentario dice "Crear 2 cocineros" pero el bucle crea 5
for (int i = 1; i <= 5; i++) { ... }
```

**Solucion:** Actualizar los comentarios para que coincidan con los numeros reales.

---

### BUG MENOR — `Sincronizacion/Ej06Filosofos/Filosofo.java` — typo y espacios

```java
// ACTUAL — falta espacio y typo "pensado" en lugar de "pensando"
System.out.println("Filosofo " + comensal + "esta pensado.");
System.out.println("Filosofo " + comensal + "esta comiendo.");

// CORRECTO
System.out.println("Filosofo " + comensal + " esta pensando.");
System.out.println("Filosofo " + comensal + " esta comiendo.");
```

---

### BUG MENOR — `Tema19ContructorProcesos/src/main/java/ejercicio08.java` — nombre incorrecto

El archivo se llama `ejercicio08.java` (minuscula) pero la clase publica debe seguir `PascalCase`. Renombrar a `Ejercicio08.java`. Puede causar errores en algunos compiladores y herramientas.

---

### AVISO — `Concurrencia/Teoria/Main.java` — acento en nombre de paquete

```java
package Teoría;  // La "í" con acento puede causar problemas en Windows
```

Algunos sistemas de ficheros y herramientas Java tienen problemas con caracteres no-ASCII en nombres de paquete. Cambiar a `package Teoria;`.

---

### AVISO — Emojis en salida de consola (`Tema17ProgramacionFuncional/`)

```java
System.out.println("\n🟢 Producto más barato: " + p);
System.out.printf("💰 Total Frutas: %.2f €\n", totalFrutas);
```

En terminales Windows sin UTF-8 configurado (defecto), los emojis se muestran como `?`. Solucion: ejecutar `chcp 65001` antes, o eliminar los emojis del codigo.

---

### AVISO — `Hilos/Ejercicio01Thread/Main.java` — pseudo-codigo en comentario

```java
/*
    .run()
    .start()
    .sleep(long milis) -> metodo stático de thread...
    .join() -> Sirve para que un hilo Espere...
*/
```

No es error de compilacion pero es confuso. Convertir a comentario en prosa o mover al README.

---

## 4. Temas que faltan por desarrollar

### CRITICO — JDBC (Acceso a bases de datos)

Es uno de los modulos centrales de 2ºDAM "Acceso a Datos". Hay un PDF de JDBC en `AccesoDatos/docs/` que indica que estaba planificado pero nunca se implemento.

**Que incluir:**
```java
// Conexion basica
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mi_bd", "usuario", "password");

// PreparedStatement (SIEMPRE esto — nunca Statement raw para evitar SQL injection)
PreparedStatement ps = conn.prepareStatement(
    "SELECT * FROM clientes WHERE id = ?");
ps.setInt(1, 5);
ResultSet rs = ps.executeQuery();
while (rs.next()) {
    System.out.println(rs.getString("nombre"));
}
```

**Ejercicios propuestos:**
1. Conectar a BD y hacer SELECT basico
2. CRUD completo de una entidad (Cliente, Producto...)
3. Patron DAO: separar logica de BD de la de negocio
4. Transacciones: `setAutoCommit(false)`, `commit()`, `rollback()`
5. Connection Pool con HikariCP

---

### CRITICO — Testing con JUnit 5

No hay ni un test en todo el repo. En 2ºDAM se exige testing. JUnit 5 es el estandar actual.

**Estructura propuesta:**
```
10-testing/
├── pom.xml                          ← JUnit 5 + Mockito como dependencias
└── src/
    ├── main/java/
    │   ├── calculadora/Calculadora.java
    │   └── banco/CuentaBancaria.java  ← mismas clases del tema de sincronizacion
    └── test/java/
        ├── calculadora/CalculadoraTest.java
        └── banco/CuentaBancariaTest.java
```

**Ejercicios propuestos:**
1. Tests unitarios de la clase `Contador` de Sincronizacion
2. Tests de las operaciones CRUD de AccesoDatos
3. `@ParameterizedTest` para los algoritmos de planificacion
4. Mock de la conexion JDBC con Mockito

---

### IMPORTANTE — Serializacion de objetos

El archivo `AccesoDatos/data/datos.dat` sugiere que estaba planificada pero nunca implementada.

```java
// Serializar (guardar objeto en fichero binario)
try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("datos.dat"))) {
    oos.writeObject(miObjeto);
}

// Deserializar
try (ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream("datos.dat"))) {
    MiClase obj = (MiClase) ois.readObject();
}
```

**Ejercicios propuestos:**
1. Serializar y deserializar un objeto simple
2. Lista de objetos en un fichero `.dat`
3. `Serializable` vs `Externalizable`
4. `serialVersionUID` y compatibilidad de versiones

---

### IMPORTANTE — Sockets TCP en Tema18

`Tema18ProgramacionRed/` solo tiene `Conexion.java` (clase URL) y el esqueleto de servidores. Faltan ejercicios funcionales de cliente-servidor.

**Ejercicios propuestos:**
1. Servidor eco: recibe un mensaje y lo devuelve en mayusculas
2. Servidor de hora: el cliente pide la hora, el servidor responde
3. Servidor concurrente: un hilo por cliente con `ExecutorService`
4. Chat simple: varios clientes conectados a un servidor central
5. Protocolo de aplicacion custom: texto tipo HTTP basico

---

### IMPORTANTE — Colecciones avanzadas y Generics

Prerequisito real para los Streams. Muchos alumnos llegan sin dominarlos.

**Que cubrir:**
- `ArrayList` vs `LinkedList`: cuando usar cada una
- `HashMap` vs `TreeMap` vs `LinkedHashMap`
- `HashSet`, `TreeSet`: operaciones de conjunto
- `Collections`: `sort()`, `reverse()`, `shuffle()`, `frequency()`
- Generics: `<T>`, `<? extends T>`, `<? super T>`

---

### UTIL — Logging profesional

`System.out.println` no es logging. En proyectos reales se usa un framework.

```java
// java.util.logging (sin dependencias)
private static final Logger LOG = Logger.getLogger(MiClase.class.getName());
LOG.info("Mensaje informativo");
LOG.warning("Advertencia");
LOG.severe("Error grave");

// SLF4J + Logback (la opcion profesional, requiere Maven)
private static final Logger log = LoggerFactory.getLogger(MiClase.class);
log.info("Proceso iniciado con {} parametros", n);
log.error("Error al conectar: {}", e.getMessage());
```

---

### UTIL — Patrones de diseño basicos

Para 2ºDAM son exigibles: Singleton, Factory, DAO, Observer.

Ya hay un ejemplo de DAO en AccesoDatos (ejercicio02Bonana). Falta documentarlo como patron y añadir los demas.

---

## 5. Mejoras propuestas por tema

### Hilos

| Mejora | Descripcion |
|--------|------------|
| Añadir bloque C con `ExecutorService` | Los bloques A y B usan `Thread` raw. Falta `newFixedThreadPool`, `newCachedThreadPool`, `ScheduledExecutorService` |
| `CompletableFuture` | El uso moderno de concurrencia en Java — fundamental para backend y APIs |
| `Callable` + `Future` | Solo se menciona en teoria, no hay ejercicio especifico |
| Diagrama de estados en README | Ciclo de vida ASCII: NEW → RUNNABLE → RUNNING → BLOCKED → TERMINATED |
| Ejercicio `AtomicInteger` con explicacion | `ejercicio2110` lo usa pero sin explicar por que es mejor que `volatile int` |

### Sincronizacion

| Mejora | Descripcion |
|--------|------------|
| Ejercicio deadlock intencional | Mostrar el deadlock con dos hilos y dos locks — luego solucionarlo con orden de adquisicion |
| `ReadWriteLock` | `ReentrantReadWriteLock`: multiples lectores, un escritor — muy util para caches |
| `Semaphore` de `java.util.concurrent` | Ya hay uno manual en Hilos/ejercicio219 pero el real es el de la JDK |
| `CountDownLatch` | Esperar a que N hilos terminen antes de continuar |
| `CyclicBarrier` | Punto de sincronizacion donde todos los hilos esperan |

### Concurrencia

| Mejora | Descripcion |
|--------|------------|
| **Completar `Productor.java`** | CRITICO — sin esto el ejercicio no funciona |
| **Anadir `producir()` a `Buffer.java`** | CRITICO — solo existe `consumir()` |
| `BlockingQueue` | `java.util.concurrent.BlockingQueue` — version moderna de Productor-Consumidor sin `wait/notify` manual |
| `LinkedBlockingQueue` | Ver como `put()` y `take()` hacen lo mismo que el buffer manual |

### AlgoritmosOrdenacion

| Mejora | Descripcion |
|--------|------------|
| **Corregir formula `retorno`** | `retorno = finalizacion - llegada` en SJF, SRTF y FIFO |
| Diagrama de Gantt en FIFO y SJF | RR y SRTF ya tienen Gantt. FIFO y SJF deberian tambien |
| Clase base `Proceso` | Los 4 algoritmos tienen los mismos arrays. Refactorizar con una clase comun |
| Tests de los algoritmos | Verificar con ejemplos conocidos del libro |
| Añadir HRRN | Highest Response Ratio Next — para completar el temario de planificacion |

### AccesoDatos

| Mejora | Descripcion |
|--------|------------|
| **Implementar JDBC** | CRITICO — hay PDF pero no hay codigo |
| Ejercicio JSON con Jackson | `biblioteca.json` existe pero no hay codigo que lo lea |
| Patron Repository | Evolucion del patron DAO para JDBC |
| Ejercicio con `Files.walk()` | Recorrer un directorio y procesar todos los .txt o .csv |
| Fichero de propiedades | Leer configuracion desde `.properties` — muy comun en proyectos reales |

### Swing

| Mejora | Descripcion |
|--------|------------|
| **Corregir bug BorderLayout + null** | En `Ej01Saludo.java` lineas 31-32 |
| `SwingUtilities.invokeLater()` | Mover la creacion de la ventana al EDT en todos los `main()` |
| `JTable` | Mostrar datos tabulares — muy pedido en examenes |
| `JDialog` | Ventana de dialogo personalizada (alternativa a `JOptionPane`) |
| `JFileChooser` | Seleccionar fichero desde la UI — conecta con AccesoDatos |
| Ejercicio CRUD completo en Swing | Tabla de productos con Añadir, Editar, Borrar — integra Swing + AccesoDatos |

### Programacion Funcional

| Mejora | Descripcion |
|--------|------------|
| Unificar nomenclatura | Algunos ejercicios en carpeta `Ejercicio01/`, otros como `Ejercicio01Password.java` sueltos — decidir un convenio |
| Añadir `Collectors.joining()` | Muy comun en examenes |
| Añadir `flatMap()` | Stream de streams — donde muchos alumnos se atascan |
| Añadir `reduce()` | Operacion terminal fundamental que falta en los ejemplos |
| Ejercicio `Map.Entry` streams | Ejercicio12 lo roza pero merece ejercicio propio |

### Programacion de Red

| Mejora | Descripcion |
|--------|------------|
| **Servidor eco funcional** | El esqueleto existe pero no hay cliente para probarlo |
| Chat multiusuario | El ejercicio mas completo — integra hilos + sockets |
| Consumir API REST | `HttpClient` (Java 11+) para hacer GET a una API real |
| Parsear respuesta JSON | Combinado con AccesoDatos (Jackson/Gson) |

---

## 6. Problemas estructurales del repo

### Solapamiento `ProcessProcessBuilder/` vs `Tema19ContructorProcesos/`

Ambas carpetas cubren `ProcessBuilder` con ejercicios similares. No queda clara la diferencia.

**Propuesta:** Unificarlas en `07-procesos/`:
- `ProcessProcessBuilder/` → ejemplos didacticos sueltos (Ej00 a Ej20)
- `Tema19ContructorProcesos/` → proyecto Maven estructurado

**Alternativa conservadora:** Renombrar a `07a-procesos-ejemplos/` y `07b-procesos-maven/` con nota en los READMEs explicando la diferencia.

---

### Inconsistencia en prefijos de carpeta

- Con prefijo: `Tema17ProgramacionFuncional/`, `Tema18ProgramacionRed/`, `Tema19ContructorProcesos/`
- Sin prefijo: `Hilos/`, `Sincronizacion/`, `Concurrencia/`, `AlgoritmosOrdenacion/`, `Swing/`, `AccesoDatos/`

**Propuesta:** Adoptar prefijos numericos en todos:
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

Renombrar con `git mv` para preservar historial.

---

### Dos series de ejercicios en Hilos sin conexion explicita

- **Serie A:** `Ejercicio01Thread/`, ..., `Ejercicio08TresHilosOrden/`
- **Serie B:** `ejercicio131/`, `ejercicio142/`, ..., `ejercicio2212/`

La Serie B parece de un PDF o libro (por los numeros 13.1, 14.2...) pero no hay ningun archivo que explique la relacion entre ambas.

**Propuesta:** Añadir en `Hilos/README.md` la tabla de equivalencia y separar visualmente los dos grupos.

---

### Falta proyecto integrador

El curriculum de 2ºDAM incluye un proyecto final que integra varios modulos. No hay ninguno en el repo.

**Propuesta:**
```
proyectos/
└── gestion-empleados/
    ├── README.md
    ├── pom.xml
    └── src/
        ├── main/java/
        │   ├── modelo/Empleado.java
        │   ├── dao/EmpleadoDAO.java          ← JDBC
        │   ├── servicio/EmpleadoService.java  ← logica de negocio
        │   ├── ui/MainWindow.java             ← Swing
        │   └── Main.java
        └── test/java/
            └── EmpleadoServiceTest.java       ← JUnit 5
```

---

## 7. Convenios de codigo

Estos convenios deben ser consistentes en todos los archivos del repo:

### Naming

| Elemento | Convenio | Ejemplo |
|---------|----------|---------|
| Clase | PascalCase | `CuentaBancaria` |
| Metodo | camelCase | `obtenerSaldo()` |
| Variable | camelCase | `tiempoRestante` |
| Constante | UPPER_SNAKE_CASE | `MAX_INTENTOS` |
| Paquete | lowercase sin acentos | `ejercicio01`, `productorconsumidor` |
| Archivo | Igual que la clase publica | `CuentaBancaria.java` |

**Violaciones actuales:**
- `ejercicio08.java` en Tema19 → debe ser `Ejercicio08.java`
- Paquetes con mayusculas: `package Fifo`, `package RR`, `package Ej01Contador` → lowercase
- `package Teoría` → `package Teoria` (sin acento)

### Estructura de clases

```java
// Orden dentro de una clase:
public class MiClase {
    // 1. Constantes (static final)
    private static final int MAX = 10;

    // 2. Campos de instancia (privados)
    private int valor;

    // 3. Constructores
    public MiClase(int valor) { this.valor = valor; }

    // 4. Metodos publicos
    public int getValor() { return valor; }

    // 5. Metodos privados (auxiliares)
    private void calcular() { }
}
```

### Manejo de excepciones

```java
// MAL — silenciar excepciones
} catch (Exception e) { }

// MAL — en produccion no es suficiente
} catch (IOException e) { e.printStackTrace(); }

// BIEN
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

// MAL — si hay excepcion, close() nunca se llama
BufferedReader br = Files.newBufferedReader(ruta);
br.close();
```

---

## 8. Hoja de ruta

### Prioridad 1 — Bugs criticos (hacer antes de usar el repo con alumnos)

- [ ] Completar `Concurrencia/productorConsumidor/Productor.java`
- [ ] Anadir metodo `producir()` a `Concurrencia/productorConsumidor/Buffer.java`
- [ ] Corregir formula `retorno` en `AlgoritmosOrdenacion/SJF/SJF.java`
- [ ] Corregir formula `retorno` en `AlgoritmosOrdenacion/SRTF/SRTF.java`
- [ ] Corregir bug null layout en `Swing/Ej01Saludo.java`
- [ ] Corregir comentarios en `Sincronizacion/Restaurante/Main.java` (3 camareros → 10, 2 cocineros → 5)
- [ ] Corregir typos en `Sincronizacion/Ej06Filosofos/Filosofo.java`
- [ ] Renombrar `ejercicio08.java` → `Ejercicio08.java` en Tema19
- [ ] Corregir `package Teoría` → `package Teoria` en Concurrencia

### Prioridad 2 — Temas incompletos criticos

- [ ] Implementar `AccesoDatos/ejercicio04json/` con Jackson o Gson
- [ ] Implementar `AccesoDatos/ejercicio05jdbc/` con JDBC + MySQL/MariaDB
- [ ] Implementar `AccesoDatos/ejercicio06serializacion/` con ObjectOutputStream
- [ ] Completar `Tema18ProgramacionRed/` con ejercicios de Socket cliente-servidor funcionales
- [ ] Anadir bloque de `ExecutorService`, `Callable`, `Future` a `Hilos/`

### Prioridad 3 — Temas nuevos importantes

- [ ] Crear `10-testing/` con JUnit 5 — estructura Maven + tests de clases existentes
- [ ] Crear `00-fundamentos/colecciones/` y `00-fundamentos/generics/`
- [ ] Crear `11-logging/` con java.util.logging basico y ejemplo SLF4J
- [ ] Anadir `BlockingQueue` como alternativa moderna al Productor-Consumidor manual

### Prioridad 4 — Mejoras de calidad

- [ ] Unificar nomenclatura de carpetas con prefijos numericos
- [ ] Mover `SwingUtilities.invokeLater()` a todos los `main()` de Swing
- [ ] Corregir nombres de paquetes a lowercase en AlgoritmosOrdenacion y Sincronizacion
- [ ] Anadir diagrama de Gantt a `AlgoritmosOrdenacion/Fifo/` y `AlgoritmosOrdenacion/SJF/`
- [ ] Refactorizar con clase base `Proceso` en AlgoritmosOrdenacion
- [ ] Unificar nomenclatura de ejercicios en Tema17 (carpetas vs archivos sueltos)
- [ ] Documentar conexion entre Serie A y Serie B de ejercicios en Hilos

### Prioridad 5 — Proyecto integrador

- [ ] Disenar y crear `proyectos/gestion-empleados/` integrando Swing + JDBC + JUnit 5
- [ ] Documentar como ejercicio final del modulo con enunciado claro

---

## 9. Requisitos y compilacion

**Requisitos:**
- **Java 17+** — el codigo usa `switch` expressions y text blocks de Java 14+
- **Java 24** — solo para `Tema19ContructorProcesos/` (ver `pom.xml`)
- **Maven 3.6+** — solo para `Tema19ContructorProcesos/`
- **IDE recomendado:** IntelliJ IDEA o VS Code con Extension Pack for Java

**Compilar y ejecutar sin IDE:**

```bash
# Desde la raiz del repo
javac -d out Hilos/Ejercicio01Thread/*.java
java -cp out Ejercicio01Thread.Main

# AccesoDatos (necesita la carpeta data/ en el classpath correcto)
javac -d out AccesoDatos/ejercicio01/*.java
java -cp out ejercicio01.TextoPlano

# Tema19 con Maven
cd Tema19ContructorProcesos
mvn compile
mvn exec:java -Dexec.mainClass="Ejercicio01"
mvn exec:java -Dexec.mainClass="ejercicio10.Main"
```

**Estructura general de cada tema:**

```
Tema/
├── README.md           ← Teoria + indice de ejercicios
├── Main.java           ← Apuntes teoricos en comentarios (algunos temas)
├── EjercicioXX/        ← Cada ejercicio en su carpeta
│   ├── Main.java       ← Punto de entrada
│   └── *.java          ← Clases auxiliares
└── data/               ← Archivos de datos (AccesoDatos)
```

---

*Ultima actualizacion: 2026-03-21*

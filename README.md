# Java 2 — Apuntes 2ºDAM

Repositorio de apuntes, teoría y ejercicios de Java para segundo curso de Desarrollo de Aplicaciones Multiplataforma (DAM). Cada carpeta es un tema independiente con su propio README, ejemplos funcionales y ejercicios comentados.

---

## Temario

| # | Tema | Contenido |
|---|------|-----------|
| 01 | [Hilos](./Hilos/README.md) | `Thread`, `Runnable`, `sleep`, `join`, `setPriority`, `isAlive`, ciclo de vida |
| 02 | [Sincronizacion](./Sincronizacion/README.md) | `synchronized`, zona crítica, `ReentrantLock`, deadlocks, filósofos |
| 03 | [Concurrencia](./Concurrencia/README.md) | Productor-Consumidor, `wait/notify/notifyAll`, monitor |
| 04 | [AlgoritmosOrdenacion](./AlgoritmosOrdenacion/README.md) | Planificación de procesos: FIFO, SJF, SRTF, Round Robin |
| 05 | [ProcessProcessBuilder](./ProcessProcessBuilder/README.md) | `Process`, `ProcessBuilder`, variables de entorno, redirección I/O |
| 06 | [AccesoDatos](./AccesoDatos/README.md) | Ficheros de texto, CSV, JSON, patron DAO |
| 07 | [Swing](./Swing/README.md) | `JFrame`, `JLabel`, `JButton`, `JTextField`, layouts, eventos |
| 08 | [Tema17 — Prog. Funcional](./Tema17ProgramacionFuncional/README.md) | Lambdas, `Stream`, `Optional`, referencias a métodos, `Callable` |
| 09 | [Tema18 — Prog. de Red](./Tema18ProgramacionRed/README.md) | `URL`, `Socket`, `ServerSocket`, cliente-servidor |
| 10 | [Tema19 — Constructor Procesos](./Tema19ContructorProcesos/README.md) | `ProcessBuilder` avanzado con Maven, proyecto estructurado |

---

## Requisitos

- **Java 17+** (el código usa `switch` expressions y text blocks de Java 14+; Tema19 requiere Java 24)
- **Maven 3.6+** (solo para `Tema19ContructorProcesos/`)
- **IDE recomendado:** IntelliJ IDEA o VS Code con extensión Java

---

## Cómo usar estos apuntes

1. Cada tema tiene su propia carpeta con un `README.md` que explica la teoría y lista los ejercicios en orden de dificultad.
2. Los archivos con nombre `Main.java` en la raíz de un tema contienen **teoría en forma de comentarios** — se pueden leer sin ejecutar.
3. Los archivos `Apuntes.txt` de tipo hilo (`Hilos/Apuntes.txt`) contienen resúmenes y esquemas rápidos de referencia.
4. Los ejercicios numerados van en orden progresivo de dificultad.

### Compilar y ejecutar un ejercicio (sin IDE)

```bash
# Desde la raiz del repo
javac -d out Hilos/Ejercicio01Thread/*.java
java -cp out Ejercicio01Thread.Main

# Para AccesoDatos (necesita la carpeta data/ en el classpath correcto)
javac -d out AccesoDatos/ejercicio01/*.java
java -cp out ejercicio01.TextoPlano
```

---

## Estructura general de cada tema

```
Tema/
├── README.md           <- Teoria + indice de ejercicios
├── Main.java           <- Apuntes teoricos en comentarios (algunos temas)
├── Apuntes.txt         <- Resumen rapido (algunos temas)
├── EjercicioXX/        <- Cada ejercicio en su carpeta
│   ├── Main.java       <- Punto de entrada
│   └── *.java          <- Clases auxiliares
└── data/               <- Archivos de datos (AccesoDatos)
```

---

## Temas cubiertos en 2ºDAM — DAM

- **Programacion de servicios y procesos** (hilos, sincronización, concurrencia, Process)
- **Acceso a datos** (ficheros texto/CSV/JSON, patrón DAO)
- **Interfaces graficas** (Swing)
- **Programacion funcional** (Java 8+: lambdas, streams, optionals)
- **Programacion de red** (sockets, cliente-servidor)

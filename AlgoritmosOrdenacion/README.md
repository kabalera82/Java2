# Planificacion de Procesos (Algoritmos de Ordenacion)

> Tema 04 de 2ºDAM | Relacionado con: Sistemas Operativos, Hilos

La **planificacion de procesos** es el mecanismo del SO que decide que proceso ocupa la CPU en cada momento. Los algoritmos de planificacion determinan el orden de ejecucion y afectan directamente al tiempo de espera y al rendimiento general del sistema.

---

## Conceptos clave

| Termino | Definicion |
|---------|-----------|
| **Tiempo de llegada** | Instante en que el proceso entra en la cola de listos |
| **Rafaga de CPU** | Tiempo que necesita el proceso para completarse |
| **Tiempo de finalizacion** | Instante en que el proceso termina |
| **Tiempo de retorno** | `finalizacion - llegada` — tiempo total desde que llego hasta que termino |
| **Tiempo de espera** | `retorno - rafaga` — tiempo que estuvo listo pero sin ejecutarse |
| **Quantum** | Rodaja de tiempo maxima asignada en Round Robin |
| **Apropiativo** | El SO puede interrumpir un proceso en ejecucion (SRTF, RR) |
| **No apropiativo** | El proceso ejecuta hasta terminar o bloquearse (FIFO, SJF) |

---

## Algoritmos implementados

### FIFO — First In, First Out (Primero en llegar, primero en ejecutar)

**Carpeta:** `Fifo/`

- **Tipo:** No apropiativo
- **Criterio:** Orden de llegada. El primero que llega, primero se ejecuta.
- **Ventaja:** Simple de implementar, sin inanicion.
- **Desventaja:** Convoy effect — un proceso largo bloquea a todos los cortos que llegan detras.
- **Como funciona el codigo:** Ordena por tiempo de llegada (selection sort), encadena las finalizaciones, calcula retorno y espera, imprime tabla con promedios.

```
Proceso | Llegada | Rafaga | Fin | Retorno | Espera
P1      |    0    |   5    |  5  |    5    |   0
P2      |    2    |   3    |  8  |    6    |   3
P3      |    4    |   2    | 10  |    6    |   4
```

---

### SJF — Shortest Job First (Tarea mas corta primero)

**Carpeta:** `SJF/`

- **Tipo:** No apropiativo
- **Criterio:** Entre los procesos disponibles en la cola, ejecuta el de menor rafaga.
- **Ventaja:** Minimiza el tiempo de espera promedio (optimo para no apropiativo).
- **Desventaja:** Inanicion — un proceso largo puede esperar indefinidamente si siguen llegando cortos.

---

### SRTF — Shortest Remaining Time First (Menor tiempo restante)

**Carpeta:** `SRTF/`

- **Tipo:** Apropiativo (version apropiativa de SJF)
- **Criterio:** En cada instante de tiempo, ejecuta el proceso con MENOR tiempo de CPU restante. Si llega uno mas corto, se apropia de la CPU.
- **Ventaja:** Optimo para tiempo de espera en entornos apropiiativos.
- **Desventaja:** Inanicion posible; hay que conocer los tiempos de antemano (imposible en sistemas reales).

---

### Round Robin (RR) — Turno rotatorio

**Carpeta:** `RR/`

- **Tipo:** Apropiativo
- **Criterio:** Cada proceso recibe un **quantum** de tiempo fijo. Si no termina, vuelve al final de la cola.
- **Ventaja:** Equitativo, buena respuesta para procesos cortos e interactivos.
- **Desventaja:** Mucho cambio de contexto si el quantum es muy pequeno. Si es muy grande, degenera en FIFO.
- **Clave:** Elegir un quantum adecuado es critico (tipicamente 10-100 ms en sistemas reales).

---

## Comparacion rapida

| Algoritmo | Apropiativo | Inanicion | Optimo en | Uso tipico |
|-----------|-------------|-----------|-----------|-----------|
| FIFO | No | No | Carga uniforme | Procesos batch sencillos |
| SJF | No | Si | Minimizar espera promedio | Sistemas con tiempos conocidos |
| SRTF | Si | Si | Minimizar espera (apropiativo) | Sistemas con llegadas frecuentes |
| Round Robin | Si | No | Sistemas interactivos | Sistemas de tiempo compartido |

---

## Como ejecutar

```bash
javac -d out AlgoritmosOrdenacion/Fifo/*.java
java -cp out Fifo.Main

# El programa pide por teclado el numero de procesos
# y los tiempos de llegada y rafaga de cada uno
```

---

## Ejemplo de sesion FIFO

```
Introduzca el numero de procesos: 3

Proceso P1:
Tiempo de llegada: 0
Tiempo de rafaga: 5

Proceso P2:
Tiempo de llegada: 2
Tiempo de rafaga: 3

Proceso P3:
Tiempo de llegada: 4
Tiempo de rafaga: 2

---------------------------------------------------------------
Proceso  Llegada  Rafaga  Final  Retorno  Espera
---------------------------------------------------------------
P1       0        5       5      5        0
P2       2        3       8      6        3
P3       4        2       10     6        4

Tiempo medio de retorno: 5.67
Tiempo medio de espera: 2.33
```

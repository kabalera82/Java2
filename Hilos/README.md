# Hilos — Programacion concurrente en Java

> Tema 01 de 2ºDAM | Prerequisito: Java basico (bucles, clases, interfaces)

Los hilos permiten que un programa ejecute varias tareas **en paralelo** (o intercaladas) dentro del mismo proceso. Son la base de cualquier aplicacion que necesite respuesta simultanea: servidores, UIs, descargas en segundo plano, etc.

---

## Teoria rapida

Consulta el archivo `Apuntes.txt` para el esquema completo con patrones de codigo.

### Formas de crear hilos

| Forma | Cuando usarla |
|-------|--------------|
| `extends Thread` | Solo para ejemplos didacticos y casos muy simples |
| `implements Runnable` | **La opcion correcta el 95% de las veces** — separa tarea de hilo, permite herencia multiple |
| `Callable<V> + Future<V>` | Cuando la tarea necesita **devolver un resultado** |
| `ExecutorService` (pool) | En aplicaciones reales con muchas tareas — gestiona hilos de forma eficiente |

### Metodos clave de `Thread`

| Metodo | Que hace |
|--------|---------|
| `start()` | Crea el hilo y llama a `run()` en paralelo |
| `run()` | El codigo del hilo — si lo llamas directamente NO crea concurrencia |
| `sleep(ms)` | Pausa el hilo actual N milisegundos — puede lanzar `InterruptedException` |
| `join()` | El hilo actual espera a que otro hilo termine |
| `interrupt()` | Senala al hilo para que se detenga de forma cooperativa |
| `isAlive()` | `true` si el hilo ha sido iniciado y aun no ha terminado |
| `setPriority(int)` | Cambia la prioridad (1-10, default 5) — no garantiza orden |
| `getName()` | Devuelve el nombre del hilo |

### Ciclo de vida

```
NEW → RUNNABLE → RUNNING → TERMINATED
                    ↕
              BLOCKED / WAITING / TIMED_WAITING
```

### Problemas tipicos

- **Race condition**: varios hilos modifican el mismo dato sin proteccion → resultado impredecible
- **Deadlock**: dos hilos esperan recursos del otro → nadie avanza (programa congelado)
- **Starvation**: un hilo nunca consigue CPU porque otros acaparan
- **Livelock**: los hilos cambian de estado continuamente pero ninguno progresa

---

## Ejercicios

Los ejercicios estan organizados en **dos bloques**:

### Bloque A — Serie 01 (introduccion a hilos)

| Carpeta | Concepto | Descripcion |
|---------|----------|-------------|
| `Ejercicio01Thread/` | `extends Thread` vs `implements Runnable` | Diferencia entre `run()` directo y `start()` real. Clase `MiHilo` y `EjemploRunnable` |
| `Ejercicio011RunStart/` | `run()` vs `start()` | Observar que `run()` ejecuta en el hilo actual y `start()` crea un hilo nuevo |
| `Ejercicio012Sleep/` | `sleep()` | Reloj Tic-Tac: hilo imprime cada segundo usando `Thread.sleep(1000)` |
| `Ejercicio02Sleep/` | `sleep()` con Runnable | Version Runnable del ejercicio de sleep |
| `Ejercicio03Join/` | `join()` | Garantiza que main espera a que el hilo secundario termine antes de continuar |
| `Ejercicio04SetPriority/` | `setPriority()` | Muestra que la prioridad influye pero no garantiza orden de ejecucion |
| `Ejercicio05IsAlive/` | `isAlive()` | Consultar el estado de un hilo durante su ejecucion |
| `Ejercicio06Carrera/` | Carrera de hilos | Varios hilos compiten — demuestra el no-determinismo |
| `Ejercicio07Semaforo/` | Semaforo manual | Control de acceso con `wait()`/`notify()` — paso previo a `Semaphore` |
| `Ejercicio08TresHilosOrden/` | Coordinacion de 3 hilos | Forzar que 3 hilos se ejecuten en un orden concreto con `join()` |

### Bloque B — Serie 13-22 (ejercicios avanzados numerados)

| Carpeta | Equivale a | Concepto |
|---------|-----------|----------|
| `ejercicio131/` | Ejercicio 13.1 | `Runnable` con lambda, numeracion de hilos |
| `ejercicio142/` | Ejercicio 14.2 | Hilo nuevo vs lambda |
| `ejercicio153/` | Ejercicio 15.3 | `sleep()` con Runnable |
| `ejercicio164/` | Ejercicio 16.4 | Hilo con nombre y `currentThread()` |
| `ejercicio175/` | Ejercicio 17.5 | Alta, media y baja prioridad en accion |
| `ejercicio186/` | Ejercicio 18.6 | Carrera con `Runnable` |
| `ejercicio197/` | Ejercicio 19.7 | `join()` en cadena |
| `ejercicio208/` | Ejercicio 20.8 | Hilos pares e impares intercalados |
| `ejercicio219/` | Ejercicio 21.9 | Semaforo con `Semaphore` de Java |
| `ejercicio2110/` | Ejercicio 21.10 | Contador con `AtomicInteger` |
| `ejercicio2211/` | Ejercicio 22.11 | Timer con `ScheduledExecutorService` |
| `ejercicio2212/` | Ejercicio 22.12 | Simulacion de descarga paralela |

---

## Como ejecutar

```bash
# Compilar desde la raiz del repo
javac -d out Hilos/Ejercicio01Thread/*.java

# Ejecutar
java -cp out Ejercicio01Thread.Main
```

> **Atencion:** como los archivos no forman parte de un proyecto Maven/Gradle, hay que compilar la carpeta correspondiente y usar `-cp out` con el nombre del paquete.

---

## Referencia — Patrones de codigo

```java
// 1. extends Thread (solo para ejemplos simples)
class MiHilo extends Thread {
    public void run() { System.out.println("Hilo: " + getName()); }
}
new MiHilo().start();

// 2. Runnable + Thread (recomendado para tareas sin resultado)
Runnable tarea = () -> System.out.println("Tarea en " + Thread.currentThread().getName());
new Thread(tarea).start();

// 3. Callable + Future (cuando necesitas el resultado)
ExecutorService pool = Executors.newSingleThreadExecutor();
Future<Integer> future = pool.submit(() -> { return 42; });
int resultado = future.get();
pool.shutdown();

// 4. ExecutorService con pool fijo (para muchas tareas)
ExecutorService pool = Executors.newFixedThreadPool(4);
for (int i = 0; i < 10; i++) {
    int id = i;
    pool.submit(() -> System.out.println("Tarea " + id));
}
pool.shutdown();
```

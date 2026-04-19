# Sincronizacion — Control de acceso concurrente

> Tema 02 de 2ºDAM | Prerequisito: Hilos (Tema 01)

La sincronizacion resuelve los **problemas de concurrencia** que aparecen cuando varios hilos comparten recursos. Sin ella, los datos pueden corromperse por condiciones de carrera y el programa puede quedarse congelado por deadlocks.

---

## Teoria rapida

Consulta `Main.java` en esta carpeta para la teoria completa con ejemplos de codigo.

### Zona critica

Trozo de codigo que accede a un recurso compartido y que NO puede ejecutarse simultaneamente por dos hilos. La sincronizacion protege exactamente esa zona.

### `synchronized` — las tres formas

```java
// 1. Metodo de instancia sincronizado — bloquea sobre "this"
public synchronized void incrementar() {
    contador++;
}
// Equivalente a:
public void incrementar() {
    synchronized (this) { contador++; }
}

// 2. Bloque sincronizado — mas fino, solo bloqueas lo que necesitas
public void metodo() {
    // codigo no critico (no bloquea)
    synchronized (this) {
        // zona critica
    }
    // mas codigo no critico
}

// 3. Metodo estatico sincronizado — bloquea sobre la CLASE, no sobre "this"
public static synchronized void log(String msg) {
    System.out.println(msg);
}
// Equivalente a:
public static void log(String msg) {
    synchronized (MiClase.class) { System.out.println(msg); }
}
```

| Forma | Monitor (candado) | Afecta a |
|-------|-------------------|----------|
| Metodo instancia `synchronized` | `this` | Solo ese objeto |
| Bloque `synchronized(this)` | `this` | Solo ese objeto |
| Metodo estatico `synchronized` | `NombreClase.class` | TODAS las instancias |

### `ReentrantLock` — alternativa mas flexible

```java
Lock lock = new ReentrantLock();

lock.lock();
try {
    // zona critica
} finally {
    lock.unlock(); // SIEMPRE en finally para garantizar que se libera
}
```

Ventaja: permite `tryLock()`, `lockInterruptibly()`, y `ReadWriteLock` para lecturas concurrentes.

### Problemas tipicos

- **Race condition**: sin `synchronized`, dos hilos pueden leer-modificar-escribir la misma variable y una escritura "pisa" a la otra
- **Deadlock**: Hilo A tiene lock1 y espera lock2. Hilo B tiene lock2 y espera lock1. Nadie avanza.
- **Prevencion de deadlock**: adquirir siempre los locks en el mismo orden

---

## Ejercicios

| Carpeta | Concepto | Descripcion |
|---------|----------|-------------|
| `Ej01Contador/` | `synchronized` basico | Dos hilos incrementan un contador compartido. Con y sin `synchronized` para ver la diferencia |
| `Ej02Sioncriced/` | `synchronized` en cuenta bancaria | Clase `Cuenta` con deposito sincronizado. `ClaseA` accede concurrentemente |
| `Ej03SyncBAnco/` | Banco completo | `CuentaBancaria` con `depositar`/`retirar` sincronizados, varios cajeros operando a la vez |
| `Ej04Examen/` | Problema del caldero (Savage) | Alumnos (consumidores) y un Caldero que se recarga (productor) — `wait()`/`notifyAll()` |
| `Ej05/` | `ReentrantLock` | Sistema de tickets con `ReentrantLock` — `MiLock` encapsula el lock, `TicketSystem` lo usa, `Cliente` lo consume |
| `Ej06Filosofos/` | Problema de los filosofos | Deadlock clasico: 5 filosofos compiten por 5 tenedores — solucion con orden de adquisicion de locks |
| `Restaurante/` | Restaurante completo | `Cocinero` produce platos en la `Cocina` (buffer), `Camarero` los sirve — patron Productor-Consumidor |

### Main.java (raiz de Sincronizacion/)

Archivo de **teoria pura** en comentarios. Cubre:
- Los 4 tipos de hilos y cuando usar cada uno (Thread, Runnable, Callable, ExecutorService)
- Race condition, inconsistencia de datos, escritura intercalada, rendimiento, deadlock
- Las tres formas de `synchronized` con equivalencias y diferencias

---

## Ejecucion de ejemplo

```bash
# Compilar el ejercicio del contador
javac -d out Sincronizacion/Ej01Contador/*.java

# Ejecutar
java -cp out Ej01Contador.Main

# Veras la salida intercalada de dos hilos
# Modificando el metodo incrementar() para quitar synchronized
# podras observar como el contador llega a valores incorrectos
```

---

## Referencia — Patron de uso correcto

```java
// CORRECTO: synchronized garantiza visibilidad y atomicidad
public class ContadorSeguro {
    private int valor = 0;

    public synchronized void incrementar() { valor++; }
    public synchronized int getValor()     { return valor; }
}

// INCORRECTO: sin synchronized, los hilos pueden leer/escribir en paralelo
public class ContadorRoto {
    private int valor = 0;
    public void incrementar() { valor++; }  // read-modify-write NO es atomico
}
```

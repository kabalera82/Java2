# Concurrencia — Patron Productor-Consumidor

> Tema 03 de 2ºDAM | Prerequisito: Sincronizacion (Tema 02)

El patron Productor-Consumidor es el problema clasico de coordinacion entre hilos. Un productor genera datos y los pone en un buffer compartido; un consumidor los extrae y procesa. La clave: **ni producir cuando el buffer esta lleno, ni consumir cuando esta vacio**.

---

## Teoria rapida

Consulta `Teoria/Main.java` para la explicacion detallada con ejemplos.

### Monitor

Objeto que controla el acceso exclusivo a un recurso compartido. Solo un hilo puede estar ejecutando codigo del monitor a la vez. En Java, cualquier objeto puede actuar como monitor con `synchronized`.

### `wait()`, `notify()`, `notifyAll()`

Estos metodos **siempre** se usan dentro de un bloque `synchronized` sobre el mismo objeto monitor.

```java
// Productor: espera si el buffer esta lleno
synchronized (monitor) {
    while (estaLleno) {
        monitor.wait(); // libera el lock y espera
    }
    // producir...
    monitor.notifyAll(); // despierta a los que esperan
}

// Consumidor: espera si el buffer esta vacio
synchronized (monitor) {
    while (estaVacio) {
        monitor.wait();
    }
    // consumir...
    monitor.notifyAll();
}
```

### `notify()` vs `notifyAll()`

| | `notify()` | `notifyAll()` |
|--|------------|---------------|
| Despierta | UN solo hilo al azar | TODOS los hilos en espera |
| Usar cuando | Solo hay UN tipo de hilo esperando | Hay productores Y consumidores esperando |
| Riesgo | Puede despertar al hilo incorrecto | Mas trafico, pero mas seguro |

**Regla general**: usa `notifyAll()` salvo que estes seguro de que solo hay un tipo de hilo esperando.

### Por que `while` y no `if`

```java
// MAL: si varios hilos esperan, uno puede robarte el recurso antes de que ejecutes
if (estaVacio) { wait(); }

// BIEN: revalua la condicion al despertar (spurious wakeups tambien justifican esto)
while (estaVacio) { wait(); }
```

---

## Estructura

```
Concurrencia/
├── productorConsumidor/     <- Implementacion clasica del patron
│   ├── Buffer.java          <- Buffer circular sincronizado (char[])
│   ├── Productor.java       <- Genera caracteres y los deposita
│   ├── Consumidor.java      <- Extrae y muestra los caracteres
│   └── Main.java            <- Arranca productores y consumidores
│
├── repasometodos/           <- Demostracion de notify() vs notifyAll()
│   ├── HiloEsperador.java   <- Hilo que hace wait() sobre el monitor
│   ├── HiloNotificador.java <- Lanza notify() (solo despierta uno)
│   └── HiloNotificadorTodos.java  <- Lanza notifyAll() (despierta todos)
│
└── Teoria/
    └── Main.java            <- Teoria completa: monitores, wait/notify, Productor-Consumidor
```

### `productorConsumidor/`

- `Buffer`: array de chars con metodos `producir()` y `consumir()` sincronizados. Gestiona `estaLleno` y `estaVacio` para bloquear con `wait()` y desbloquear con `notifyAll()`.
- `Productor`: genera letras aleatorias y las deposita en el buffer en un bucle.
- `Consumidor`: extrae del buffer y las imprime.

### `repasometodos/`

Demostracion clara de la diferencia entre `notify()` y `notifyAll()`:
1. Se arrancan 3 hilos que hacen `wait()` sobre el mismo monitor.
2. Se lanza `notify()` — solo UNO se despierta.
3. Se lanza `notifyAll()` — los restantes se despiertan.

---

## Como ejecutar

```bash
# Productor-Consumidor
javac -d out Concurrencia/productorConsumidor/*.java
java -cp out productorConsumidor.Main

# Repaso notify/notifyAll
javac -d out Concurrencia/repasometodos/*.java Concurrencia/Teoria/*.java
java -cp out Teoria.Main
```

---

## Referencia — Estructura minima del patron

```java
class Buffer {
    private Object dato;
    private boolean lleno = false;

    public synchronized void producir(Object d) throws InterruptedException {
        while (lleno) wait();
        dato = d;
        lleno = true;
        notifyAll();
    }

    public synchronized Object consumir() throws InterruptedException {
        while (!lleno) wait();
        lleno = false;
        notifyAll();
        return dato;
    }
}
```

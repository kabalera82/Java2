package Ej05;

public class MiLock {

    // Indica si el lock está ocupado (true) o libre (false)
    private boolean bloqueado = false;

    // Hilo que tiene actualmente el lock (opcional, solo si quieres control extra)
    // private Thread hiloActual;

    // Método para adquirir el lock
    public synchronized void lock() throws InterruptedException {
        // Mientras esté bloqueado, este hilo espera
        while (bloqueado) {
            wait(); // Libera el monitor y se queda esperando a notify/notifyAll
        }
        // Cuando sale del bucle, el lock estaba libre y lo marcamos como ocupado
        bloqueado = true;
        // hiloActual = Thread.currentThread();
    }

    // Método para liberar el lock
    public synchronized void unlock() {
        bloqueado = false;
        // hiloActual = null;
        notifyAll(); // Despierta a los hilos que estén esperando en wait()
    }
}

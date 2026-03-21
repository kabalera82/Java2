package ejercicio07Vater;

import java.util.LinkedList;
import java.util.Queue;

public class Vater {


    private boolean ocupado = false;        // Indica si el baño está ocupado (true) o libre (false)

    private final Queue<Thread> cola = new LinkedList<>();      // Cola FIFO: los hilos entran en orden de llegada

    public synchronized void orinando() {
        Thread yo = Thread.currentThread();

        cola.add(yo);   // Me apunto a la cola cuando llego

        // Mientras:
        // 1) el baño esté ocupado
        // 2) o yo NO sea el primero de la cola
        // -> me quedo esperando
        while (ocupado || cola.peek() != yo) {
            System.out.println(yo.getName() + " espera en la cola...");

            try {
                // wait() duerme el hilo y libera el bloqueo
                // NO consume CPU
                wait();
            } catch (InterruptedException e) {
                // Si me interrumpen, me quitan de la cola y salgo
                cola.remove(yo);
                return;
            }
        }

        // Cuando salgo del while:
        // - el baño está libre
        // - soy el primero de la cola
        // Salgo de la cola
        cola.remove();

        // Ocupo el baño
        ocupado = true;

        System.out.println(yo.getName() + " entra al baño...");
    }

    // Método para salir del baño
    public synchronized void sacudida() {

        // Marco baño libre
        ocupado = false;

        System.out.println(Thread.currentThread().getName() + " sale del baño.");

        // Despierto a TODOS los que esperan
        // Solo el primero podrá continuar (por la condición del while)
        notifyAll();
    }
}

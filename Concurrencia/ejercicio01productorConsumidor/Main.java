package ejercicio01productorConsumidor;

import javax.swing.plaf.TableHeaderUI;

public class Main {
    public static void main(String[] args) {

        /*
         * Esto es un Productor–Consumidor clásico:
         * - Productor: genera datos (chars) y los mete en el buffer.
         * - Consumidor: saca datos del buffer y los imprime.
         *
         * La coordinación se hace con:
         * - synchronized para exclusión mutua (no tocar estado compartido a la vez)
         * - wait() para dormir cuando el estado no permite avanzar
         * - notifyAll() para avisar cuando el estado cambia
         */

        // Recurso compartido único.
        Buffer buffer = new Buffer();

        // Se lanzan los dos hilos concurrentes compartiendo el mismo Buffer.
        Thread productor = new Thread(new Productor(buffer));
        Thread consumidor = new Thread(new Consumidor(buffer));

        productor.start();
        consumidor.start();

        // Nota: según tu código actual, NO termina nunca porque ambos run() tienen while(true).
        // (No lo cambio, solo lo remarco como consecuencia.)
    }
}

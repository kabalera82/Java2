package ejercicio02ProdConsum;

public class Main {
    public static void main(String[] args) {
        /*
         * Productor–Consumidor con buffer de 1 slot.
         *
         * - 1 productor produce números del 1 al 20
         * - 1 consumidor consume 20 números
         * - Coordinación con synchronized + wait() + notifyAll()
         */

        // Recurso compartido único.
        Buffer buffer = new Buffer();

        // Dos hilos, mismo Buffer.
        Thread productor = new Thread(new Productor(buffer));
        Thread consumidor = new Thread(new Consumidor(buffer));

        productor.start();
        consumidor.start();

        // El programa termina porque:
        // - Productor: for 1..20 -> sale
        // - Consumidor: while total<20 -> sale
        // Cuando ambos hilos no-daemon terminan, termina la JVM.
    }
}

package Ejercicio01Thread;

public class EjemploRunnable implements Runnable {
    /*
     * run() -> ejecuta el código como un método normal.
     *          No crea un hilo nuevo.
     *
     * start() -> la JVM crea un hilo nuevo y,
     *            dentro de ese hilo, se llama automáticamente a run().
     */

    @Override
    public void run() {
        System.out.println("Estoy en un Runnable: " +
                Thread.currentThread().getName());
    }
}

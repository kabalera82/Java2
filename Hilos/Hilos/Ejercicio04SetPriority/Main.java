package Ejercicio04SetPriority;

public class Main {
    public static void main(String[] args) {

        // Creamos la misma tarea (Runnable) que ejecutarán ambos hilos
        MiHilo mh = new MiHilo();

        // Creamos dos hilos distintos que ejecutan la MISMA tarea
        Thread hiloBajo = new Thread(mh, "HiloBajo");
        Thread hiloAlto = new Thread(mh, "HiloAlto");

        // ----------------------------------------------------------
        // PRIORIDADES DE HILO
        //
        // Thread.MIN_PRIORITY = 1  (prioridad más baja)
        // Thread.NORM_PRIORITY = 5 (prioridad normal)
        // Thread.MAX_PRIORITY = 10 (prioridad más alta)
        //
        // IMPORTANTE:
        // La prioridad es **solo una sugerencia al SO**.
        // En Windows NO garantiza que el hilo de prioridad alta
        // vaya a ejecutarse antes. En Linux se nota algo más.
        // ----------------------------------------------------------

        hiloBajo.setPriority(Thread.MIN_PRIORITY); // prioridad 1
        hiloAlto.setPriority(Thread.MAX_PRIORITY); // prioridad 10

        // Arrancan los hilos → empiezan a ejecutarse en paralelo
        hiloBajo.start();
        hiloAlto.start();
    }
}

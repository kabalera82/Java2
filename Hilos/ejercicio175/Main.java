package ejercicio175;

/**
 * Crea 3 hilos:
 * Uno con prioridad mínima.
 * Otro con prioridad normal.
 * Otro con prioridad máxima.
 * Cada hilo imprime su nombre 10 veces.
 * Observa el orden y explica si cambia según ejecuciones
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Hilo ejecutor --> " + Thread.currentThread().getName());
        // Muestra el hilo principal desde el que se lanzarán los demás

        HighPriority hp = new HighPriority();    // Runnable de alta prioridad
        MediumPriority mp = new MediumPriority();// Runnable de prioridad normal
        LowPriority lp = new LowPriority();       // Runnable de prioridad baja

        Thread ht = new Thread(hp, "High");      // Hilo High
        Thread mt = new Thread(mp, "Medium");    // Hilo Medium
        Thread lt = new Thread(lp, "Low");       // Hilo Low

        ht.setPriority(10);     // ht.setPriority(Thread.MAX_PRIORITY);
        mt.setPriority(5);      // mt.setPriority(Thread.NORM_PRIORITY);
        lt.setPriority(1);      // lt.setPriority(Thread.MIN_PRIORITY)

        // Lanzamos los hilos en paralelo (AHORA sí se cumple el enunciado)
        ht.start();
        mt.start();
        lt.start();

        // Fin del main (los hilos continúan ejecutándose en paralelo)
    }
}

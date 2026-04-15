/**
 * 5. Ejercicio: Prioridades
 * Crea 3 hilos:
 * Uno con prioridad mínima.
 * Otro con prioridad normal.
 * Otro con prioridad máxima.
 * Cada hilo imprime su nombre 10 veces.
 * Observa el orden y explica si cambia según ejecuciones.
 */
public class Main {
    public static void main(String[] args) {

        int[] prioridades = {Thread.MIN_PRIORITY, Thread.NORM_PRIORITY, Thread.MAX_PRIORITY};
        Thread[] hilos = new Thread[3];

        for (int i = 0; i < 3; i++) {
            Impresor tarea = new Impresor();

            // 1. Creamos el hilo y lo guardamos en su posición
            hilos[i] = new Thread(tarea, "Hilo-" + (i+1));

            // 2. Le asignamos la prioridad de nuestra lista
            hilos[i].setPriority(prioridades[i]);

            // 3. Lo arrancamos inmediatamente
            hilos[i].start();
        }
    }
}

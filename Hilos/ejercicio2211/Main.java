package ejercicio2211;

/**
 * Ejercicio: Timer casero con hilos
 * Crea un hilo que:
 * Cada segundo incremente un contador.
 * Imprima el contador como si fuera un cronómetro.
 * El main debe esperar 5 segundos y luego interrumpir el hilo.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {


    Timer timer = new Timer();
    Thread t1 = new Thread(timer);

    t1.start();
    Thread.sleep(5000);
    t1.interrupt();
    }
}

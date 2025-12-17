package ejercicio2110;

/**
 * Ejercicio: Contador compartido sin sincronización
 * Crea 2 hilos que incrementen el mismo contador 1000 veces.
 * Usa sleep(1) en algunas vueltas.
 * Observa resultados distintos cada ejecución → condición de carrera.
 * Explica por qué pasa.
 */
public class Main {
    static int contador = 0;
    public static void main(String[] args) {

        Incrementador incrementador1 = new Incrementador();
        Incrementador incrementador2 = new Incrementador();

        Thread t1 = new Thread(incrementador1);
        Thread t2 = new Thread(incrementador2);

        t1.start();
        t2.start();
        try{
            t1.join();;
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Valor final del contador: " + contador);

    }
}

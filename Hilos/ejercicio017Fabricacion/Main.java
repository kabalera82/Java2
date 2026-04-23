

/**
 * 17. Ejercicio: Fabricación de piezas
 * Tres hilos:
 * 1. Hilo A fabrica la pieza base (tarda 2s).
 * 2. Hilo B pule la pieza (tarda 1s) → solo puede empezar cuando A termine.
 * 3. Hilo C pinta la pieza (tarda 3s) → solo puede empezar cuando B termine.
 * Usa cadena de join().
 */

public class Main {

    public static void main(String[] args) throws Exception {

        Thread t1 = new Thread(new Fabricador(), "pieza");
        Thread t2 = new Thread(new Pintado(t1), "pieza fabricada");
        Thread t3 = new Thread(new Pulidor(t2), "");

        t1.start();
        t2.start();
        t3.start();


            t1.join();
            t2.join();
            t3.join();

    }
}
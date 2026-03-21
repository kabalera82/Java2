package ejercicio2917;


/**
 * 17. Ejercicio: Fabricación de piezas
 * Tres hilos:
 * 1. Hilo A fabrica la pieza base (tarda 2s).
 * 2. Hilo B pule la pieza (tarda 1s) → solo puede empezar cuando A termine.
 * 3. Hilo C pinta la pieza (tarda 3s) → solo puede empezar cuando B termine.
 * Usa cadena de join().
 */
public class Main {


    public static void main(String[] args) throws InterruptedException {
        HiloA a = new HiloA();
        HiloB b = new HiloB();
        HiloC c = new HiloC();
        Thread ta = new Thread(a);
        Thread tb = new Thread(b);
        Thread tc = new Thread(c);


        ta.start();
        ta.join();
        tb.start();
        tb.join();
        tc.start();
        tc.join();


    }
}

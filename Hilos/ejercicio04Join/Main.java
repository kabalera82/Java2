/**
 * 4. Ejercicio: join() básico
 * Crea dos hilos:
 * Hilo A tarda 3 segundos en terminar.
 * Hilo B no puede comenzar hasta que A haya terminado → usa join().
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {


        // 1. Preparamos a Juan su Moto (Hilo Moto)
        Amigo juan = new Amigo("Juan (Moto)");
        Thread moto = new Thread(juan);

        // 2. Preparamos a Antonio su Coche (Hilo Coche)
        Amigo antonio = new Amigo("Antonio (Coche)");
        Thread coche = new Thread(antonio);

        try {
            moto.start();
            moto.join();
            coche.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
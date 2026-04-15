package Ejercicio05IsAlive;

public class MiHilo implements Runnable {

    @Override
    public void run() {

        // Mensaje inicial del hilo
        System.out.println("Hilo: empiezo mi ejecución...");

        try {
            // Pausa de 1 segundo (1000 ms)
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            // Si alguien interrumpe el hilo durante el sleep
            System.out.println("Hilo interrumpido mientras dormía.");
        }

        // Mensaje final para ver que terminó
        System.out.println("Hilo: termino mi ejecución.");
    }
}

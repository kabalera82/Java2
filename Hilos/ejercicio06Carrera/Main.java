package Ejercicio06Carrera;

/**
 * 6. Ejercicio: Carrera de Hilos (con prioridades y sleep)
 * Simula una carrera entre 3 hilos:
 * Cada hilo avanza de 0 a 100.
 * Cada paso es un número aleatorio entre 1 y 5.
 * Cada hilo tiene una prioridad distinta.
 * Imprime quién llega primero.
 */
public class Main {

    public static void main(String[] args) {

        // ----------------------------------------------------
        // Distancia Maxima 100 metros
        // Distancia de incio 0 metros
        // Avanzar mínimo 1 metro y máximo 4 metros (Corregido en CarreraHilos)
        // 3 animales: Tortuga (Min), Liebre (Max), Serpiente (Norm)
        // ----------------------------------------------------

        CarreraHilos r1 = new CarreraHilos("Tortuga");
        CarreraHilos r2 = new CarreraHilos("Liebre");
        CarreraHilos r3 = new CarreraHilos("Serpiente");

        Thread h1 = new Thread(r1);
        Thread h2 = new Thread(r2);
        Thread h3 = new Thread(r3);

        // Asignación de Prioridades
        h1.setPriority(Thread.MIN_PRIORITY); // 1 (Menor)
        h2.setPriority(Thread.MAX_PRIORITY); // 10 (Mayor, más favorecida)
        h3.setPriority(Thread.NORM_PRIORITY); // 5 (Normal)

        System.out.println("🏁 ¡Comienza la carrera! (Liebre tiene máxima prioridad)");

        // Iniciar los hilos
        h1.start();
        h2.start();
        h3.start();

        // Esperar a que todos los hilos terminen (cuando lleguen a la meta)
        try{
            h1.join();
            h2.join();
            h3.join();
        } catch (InterruptedException e) {
            // Manejo de la interrupción del hilo principal
            System.err.println("El hilo principal ha sido interrumpido.");
        }

        System.out.println("\n✅ La carrera ha finalizado.");
    }
}
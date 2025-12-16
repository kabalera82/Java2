package ejercicio186;

import org.w3c.dom.ls.LSOutput;

/**
 * Ejercicio: Carrera de Hilos (con prioridades y sleep)
 * Simula una carrera entre 3 hilos:
 * Cada hilo avanza de 0 a 100.
 * Cada paso es un número aleatorio entre 1 y 5.
 * Cada hilo tiene una prioridad distinta.
 * Imprime quién llega primero.
 */

public class Main {

    public static void main(String[] args) {

        System.out.println("empieza la carrera");

        Corredor c1 = new Corredor();
        Corredor c2 = new Corredor();
        Corredor c3 = new Corredor();

        Thread h1 = new Thread(c1, "Rapido");
        Thread h2 = new Thread(c2, "Normal");
        Thread h3 = new Thread(c3, "Lento");

        h1.setPriority(Thread.MAX_PRIORITY); //10
        h2.setPriority(5);// (Thread.NORM_PRIORITY)
        h3.setPriority(Thread.MIN_PRIORITY); // 1

        h1.start();
        h2.start();
        h3.start();






    }
}

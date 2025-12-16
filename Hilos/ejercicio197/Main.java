package ejercicio197;

import ejercicio186.Corredor;

/**
 *  Ejercicio: Tres tareas que deben ejecutarse en orden
 * Tienes 3 hilos:
 * Hilo 1 debe ejecutar primero.
 * Hilo 2 debe esperar a que el 1 termine → join.
 * Hilo 3 debe esperar al 2 → join.
 * Cada hilo imprime un mensaje.
 */
public class Main {

    public static void main(String[] args) {
        Corredor c1 = new Corredor();
        Corredor c2 = new Corredor();
        Corredor c3 = new Corredor();
        Thread t1 = new Thread(c1, " Primer Programa");
        Thread t2 = new Thread(c2," Segundo Programa");
        Thread t3 = new Thread(c3, " Tercer Programa");


        try {
            System.out.println("Iniciando " + t1.getName());
            t1.start();
// --> Detenemos el Main aquí hasta que t1 muera
            t1.join();
            System.out.println(t1.getName() + " ha terminado.");
            System.out.println("Iniciando " + t2.getName());
// --> Iniciamos aqui para asegurarnos
            t2.start();
// --> Detenemos el Main aquí hasta que t1 muera
            t2.join();
            System.out.println(t2.getName() + " ha terminado.");
            System.out.println("Iniciando " + t3.getName());
            t3.start();
            t3.join();
            System.out.println(t3.getName() + " ha terminado.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

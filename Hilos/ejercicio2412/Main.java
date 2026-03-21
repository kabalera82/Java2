package ejercicio2412;

/**
 * 12. Ejercicio: Descargar archivos simulados
 * Simula 3 descargas:
 * Cada descarga es un hilo.
 * Cada hilo tarda entre 1 y 5 segundos (sleep).
 * El hilo main NO puede continuar hasta que todas las descargas terminen → join()
 * sobre cada una.
 */

public class Main {
    public static void main(String[] args) {

        Download d1 = new Download();
        Download d2 = new Download();
        Download d3 = new Download();
        Thread t1 = new Thread(d1);
        Thread t2 = new Thread(d2);
        Thread t3 = new Thread(d3);

        t1.start();
        t2.start();
        t3.start();

        try {
            System.out.println("Main → esperando a que terminen las descargas...");
            t1.join();
            t2.join();
            t3.join();
            System.out.println("Main → todas las descargas finalizadas");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

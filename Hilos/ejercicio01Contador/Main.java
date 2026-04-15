/**
 * 1. Ejercicio: Diferencia entre run() y start()
 * Crea un programa que:
 * Tenga un hilo que imprime números del 1 al 5.
 * Llama a run() primero.
 * Luego llama a start().
 * Observa el orden de ejecución.
 * Explica por qué ocurre.
 */


public class Main {

    public static void main(String[] args) {

        Impresor h1 = new Impresor();
        System.out.println("---- llamando a run() ---");
        h1.run();
        System.out.println("--- llamando a start()");
        h1.start();

        System.out.println("El hilo Main a terminado su trabajo");
    }
}

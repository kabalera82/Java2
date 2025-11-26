package Ejercicio011RunStart;
    /*
    - Ejercicio: Diferencia entre run() y start()
    Crea un programa que:
    Tenga un hilo que imprime números del 1 al 5.
    Llama a run() primero.
    Luego llama a start().
    Observa el orden de ejecución.
    Explica por qué ocurre.
    */


public class Main {

    public static void main(String[] args) {

        Hilos h1 = new Hilos(); // Creamos la TAREA (Runnable)

        Thread hilo1 = new Thread(h1);

        hilo1.run(); // Ejecuta el codigo en el hilo actual
        hilo1.start(); // pone en marcha otro hilo


    }
}

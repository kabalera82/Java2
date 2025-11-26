package Ej01Contador;

/*
 * Clase principal que crea un contador compartido
 * y dos hilos que lo incrementan de forma concurrente.
 */


public class Main {
    public static void main(String[] args) {
        // Creamos una única instancia de Contador que será compartida por los dos hilos
        Contador c = new Contador();

        // Creamos el primer hilo, pasándole el objeto Contador para que lo use en su run()
        // y le damos un nombre identificativo "Hilo-1"
        Thread h1 = new Thread(c, "Hilo-1");

        // Creamos el segundo hilo que también usará el mismo Contador compartido
        Thread h2 = new Thread(c, "Hilo-2");

        // Ponemos en marcha los dos hilos (empiezan a ejecutar el método run() de Contador)
        h1.start();
        h2.start();

        try {
            // join() hace que el hilo principal espere a que h1 termine
            h1.join();
            // join() hace que el hilo principal espere a que h2 termine
            h2.join();
        } catch (InterruptedException e) {
            // Si el hilo principal es interrumpido mientras espera, se lanza esta excepción
            throw new RuntimeException(e);
        }

        // Cuando los dos hilos han acabado, mostramos el valor final del contador
        System.out.println("Valor final = " + c.getValor());
    }
}

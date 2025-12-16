package ejercicio208;
/*
Ejercicio: Imprimir números pares e impares
Hilo A imprime solo los pares del 0 al 20.
Hilo B imprime solo los impares del 1 al 19.
El main debe esperar a que ambos terminen (join).
Cada hilo debe hacer un sleep(200) entre número y número.
*/

public class Main {

    public static void main(String[] args) {
/**
 * Par e Impar en este caso heredan directamente de Thread
 * por lo que no es necesario envolverlo y crear un objeto de la
 * clase Thread que lo contenga
 */
        Impares i = new Impares();
        Pares p = new Pares();
        i.start();
        p.start();
        try {
            i.join();
            i.join();

        } catch (InterruptedException e) {
            // Si el hilo principal es interrumpido mientras espera, se lanza esta excepción
            throw new RuntimeException(e);
        }
        System.out.println("--- Fin del programa principal ---");
    }
}

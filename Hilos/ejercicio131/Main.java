package ejercicio131;

/**
 * Crea un programa que:
 * Tenga un hilo que imprime números del 1 al 5.
 * Llama a run() primero
 * Luego llama a start()
 * Observa el orden de ejecución.
 * Explica por qué ocurre.
 */



public class Main {

    public static void main(String[] args) {

        HiloNum n = new HiloNum();

        n.run();
        /*
        n.run se ejecuta como un método normal, dentro del hilo main
        exactamente igual que si tuvieramos una clase normal con un método
        n.start sí le dice a la JVM que cree un nuevo hilo y ejecuta su run en
        ese hilo (@Override public void run (recuerda)
         */
        n.start();
        /*
        Crea un nuevo hilo de ejecucion y ese nuevo hilo llama internamente a run() y en paralelo al main
         */

    }
}

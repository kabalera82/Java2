
/**
 * 8. Ejercicio: Imprimir números pares e impares
 * Hilo A imprime solo los pares del 0 al 20.
 * Hilo B imprime solo los impares del 1 al 19.
 * El main debe esperar a que ambos terminen (join).
 * Cada hilo debe hacer un sleep(200) entre número y número.
 */
public class Main {

    public static void main(String[] args) {


        Thread t1 = new Thread(new Impar(), "Impar");
        Thread t2 = new Thread(new Par(), "Par");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(Thread.currentThread().getName()+ " a terminado");



    }
}

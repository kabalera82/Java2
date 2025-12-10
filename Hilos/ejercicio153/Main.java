package ejercicio153;

/**
 * Crea un hilo que tarde 2 segundos en terminar.
 * Desde el main, antes de start(), comprueba isAlive().
 * Justo después de start(), vuelve a comprobar.
 * Después de join(), comprueba otra vez
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {


        HiloLento t1 = new HiloLento();
            System.out.println("Antes de start: " + t1.isAlive());
            t1.start();
            System.out.println("Después de start: " + t1.isAlive());
            t1.join(); // -> El hilo actual espera a que otro hilo termine.
            System.out.println("Despues de Join: " + t1.isAlive());


    }
}

package ejercicio2614;

/**
 * Ejercicio: Control de proceso con isAlive()
 * Un hilo va procesando 10 pasos con sleep(400).
 * Mientras tanto, el main:
 * Cada 300 ms imprime si el hilo sigue vivo o ya ha terminado usando isAlive().
 * Cuando termine, imprime "Proceso acabado".
 */
public class Main {

    public static void main(String[] args) {

        Pasos p = new Pasos();
        Thread t1 = new Thread(p);

        t1.start();
        System.out.println("Iniciamos " + t1.getName());

            try {
                do {
                    Thread.sleep(500);
                    System.out.println("¿El hilo "
                            + t1.getName()
                            + "esta vivo?"
                            + t1.isAlive());
                } while (t1.isAlive());
            } catch (InterruptedException e) {
                System.out.println("Interrumpido");
            }


        System.out.println(Thread.currentThread().getName()+
                " programa finalizado.");
    }
}

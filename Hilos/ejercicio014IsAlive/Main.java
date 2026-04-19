/**
 * 14. Ejercicio: Control de proceso con isAlive()
 * Un hilo va procesando 10 pasos con sleep(400).
 * Mientras tanto, el main:
 * •
 * Cada 300 ms imprime si el hilo sigue vivo o ya ha terminado usando isAlive().
 * •
 * Cuando termine, imprime "Proceso acabado".
 */


public class Main {

    public static void main(String[] args) {

        Runnable pasos = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Paso - - - " + i + " - - -");
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread t1 = new Thread(pasos);

        t1.start();
        while(t1.isAlive()){
            try {
                Thread.sleep(300);
                System.out.println(Thread.currentThread().getName() + " ¿esta vivo? " + Thread.currentThread().isAlive());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(t1.getName() + " ¿esta vivo? " + t1.isAlive());


    }


}

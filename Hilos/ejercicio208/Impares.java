package ejercicio208;


public class Impares extends Thread {

    @Override
    public void run(){
        try {
            for (int i = 0; i < 100; i++) {
                if (i % 2 != 0) {
                    System.out.println(" " + i + " ");
                    sleep(200);
                }
            }
        } catch (InterruptedException e) {
            // Si el hilo principal es interrumpido mientras espera, se lanza esta excepción
            throw new RuntimeException(e);
        }

    }
}

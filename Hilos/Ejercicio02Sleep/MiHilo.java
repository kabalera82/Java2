package Ejercicio02Sleep;

public class MiHilo implements Runnable {


    @Override
    public void run() {
        try {
            System.out.println("Empiezo a trabajar en: " + Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Interrumpido mientras dormia");
        }
    }
}

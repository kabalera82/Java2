package Ejercicio02Sleep;

public class Main {



    public static void main(String[] args) {

        // Metodo SLEEP(ms)
        MiHilo mh = new MiHilo();
        Thread hilo = new Thread(mh);

        hilo.start();
        System.out.println("Empiezo a trabajar en: " + Thread.currentThread().getName());

    }
}

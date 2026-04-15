package Ejercicio07Semaforo;

public class Main {

    public static void main(String[] args) {

        // Crear un hilo semáforo
        Semaforo sm1 = new Semaforo();
        Thread h1 = new Thread(sm1); // ✔ CORRECTO

        h1.start();

        // Mensaje paralelo
        for (int i = 0; i <=8; i++) {
            System.out.println("Semáforo funcionando");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Se ha interrumpido el muestreo del mensaje");
            }
        }
    }
}

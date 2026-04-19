/**
 * 11. Ejercicio: Timer casero con hilos
 * Crea un hilo que:
 * Cada segundo incremente un contador.
 * Imprima el contador como si fuera un cronómetro.
 * El main debe esperar 5 segundos y luego interrumpir el hilo
 */


public class Main {

    public static int segundero = 0;
    public static void main(String[] args) {

        // Definimos el hilo trabajador
        Thread cronometro = new Thread(()->{
            try {
                // implementamos la bandera de cuando este hilo se interrumpa
                while (!Thread.currentThread().isInterrupted()){
                    Thread.sleep(1000);
                    segundero++;
                    if(segundero%2==0){
                        System.out.println(" - - - tic - - - ");
                    } else {
                        System.out.println(" - - - tac - - - ");
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido");
            }
            System.out.println("Hilo finalizado");
        });

        cronometro.start();

        try {
            System.out.println("Main esperando 5 seg");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main: ¡Tiempo agotado! Parando cronometro");
        // Interrumpimos el cronometro
        cronometro.interrupt();


    }
}

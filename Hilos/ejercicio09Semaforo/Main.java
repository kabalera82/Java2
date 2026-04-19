/**
 * 9. Ejercicio: Simular un semáforo simple
 * Crea un hilo que imprima:
 * VERDE → espera 2s
 * AMARILLO → espera 1s
 * ROJO → espera 3s
 * Repítelo 3 ciclos.
 * Mientras tanto, el main imprime "Semáforo funcionando…" cada 500 ms.
 */

public class Main {

    public static void main(String[] args) {

        Thread hilo = new Thread(new Semaforo(), "Semaforo");
        hilo.start();

        // Mientras el semaforo esta encendido
        while (hilo.isAlive()) {
            try {
                Thread.sleep(500);
                System.out.println("Semaforo encendido");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
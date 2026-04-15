

/**
 * 2. Ejercicio: Pausa de un hilo con sleep()
 * Haz un hilo que:
 * Imprima "tic" cada 500 ms.
 * Cuando haya impreso 5 veces, termine.
 * Mientras tanto, el hilo main imprimirá “MAIN sigue…” cada 300 ms.
 */



public class Main {

    public static void main(String[] args) {

        MiHilo nuevoHilo = new MiHilo();
        Thread h1 = new Thread(nuevoHilo);
        h1.start();

        for(int i=1;i<=5;i++){
            try {
                Thread.sleep(300);
                System.out.println("--- Main se frena ---");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

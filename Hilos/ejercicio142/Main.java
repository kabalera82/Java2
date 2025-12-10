package ejercicio142;

/**
 * Haz un hilo que:
 * Imprima "tic" cada 500 ms.
 * Cuando haya impreso 5 veces, termine.
 * Mientras tanto, el hilo main imprimirá “MAIN sigue…” cada 300 ms.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
       HiloNuevo h = new HiloNuevo();
       h.start();
        for(int i = 0;i<5;i++) {
            Thread.sleep(300);
            System.out.println(Thread.currentThread().getName() + " sigue...");
        }
    }
}

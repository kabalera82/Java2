package repasometodos;

public class HiloEsperador implements Runnable {

    /*
     * wait() solo puede llamarse dentro de un bloque synchronized
     * sobre el mismo objeto que se usa para hacer wait().
     */
    private final Object monitor;

    public HiloEsperador(Object monitor){
        this.monitor = monitor;
    }

    @Override
    public void run() {
        synchronized (monitor) {
            try {
                System.out.println(Thread.currentThread().getName() + " -> Esperando...");
                monitor.wait(); // libera el monitor y espera
                System.out.println(Thread.currentThread().getName() + " -> REANUDADO");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // buena práctica
                System.out.println("Hilo interrumpido");
            }
        }
    }
}

package Ej02Sioncriced;

// Clase que usaremos como tarea de los hilos
public class ClaseA implements Runnable {

    private final Cuenta cuenta;
    private final boolean usarMetodo1; // true = incrementar(), false = incrementar2()

    public ClaseA(Cuenta cuenta, boolean usarMetodo1) {
        this.cuenta = cuenta;
        this.usarMetodo1 = usarMetodo1;
    }

    @Override
    public void run() {
        // Cada hilo hace 5 incrementos
        for (int i = 0; i < 5; i++) {
            if (usarMetodo1) {
                cuenta.incrementar();   // método sincronizado
            } else {
                cuenta.incrementar2();  // bloque synchronized(this)
            }

            // Ejemplo de uso de método estático sincronizado para log global
            Main.log("Log global desde " + Thread.currentThread().getName());

            try {
                Thread.sleep(50); // pequeña pausa para ver mejor el entrelazado
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

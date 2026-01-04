package ejercicio2213;

public class Nombrado implements Runnable {

    @Override
    public void run() {
        String nombre = Thread.currentThread().getName();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(nombre + " ejecutándose (prioridad "
                        + Thread.currentThread().getPriority() + ")");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            // nada
        }
    }
}

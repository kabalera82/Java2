package ejercicio175;

public class MediumPriority implements Runnable {

    @Override
    public void run() {
        // Bucle que imprime 10 veces el nombre y prioridad del hilo
        for (int i = 0; i < 10; i++) {
                System.out.println(
                        "Hilo: " +
                                Thread.currentThread().getName() +
                                " | Prioridad: " + Thread.currentThread().getPriority() +
                                " | Iteración: " + i
                );
        }
    }
}
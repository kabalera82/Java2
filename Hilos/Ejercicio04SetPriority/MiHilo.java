package Ejercicio04SetPriority;

public class MiHilo implements Runnable {

    @Override
    public void run() {

        // Bucle que imprime información del hilo actual
        for (int i = 0; i < 5; i++) {

            // Thread.currentThread() → devuelve el hilo que está ejecutando este código
            System.out.println(
                    "Hilo: " + Thread.currentThread().getName() +   // nombre del hilo
                            " | Prioridad: " + Thread.currentThread().getPriority() + // prioridad asignada
                            " | i=" + i                                   // contador del bucle
            );

            // Puedes añadir una pequeña pausa para ver mejor el efecto
            // try { Thread.sleep(10); } catch (Exception e) {}
        }
    }
}

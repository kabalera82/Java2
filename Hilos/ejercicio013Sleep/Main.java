import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Runnable tarea = () -> {
            // Quitamos el sleep. Ahora los hilos corren sin pausa.
            // Usamos isInterrupted() para que el bucle pueda detenerse al final.
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Hilo " + Thread.currentThread().getName() +
                        " - Prioridad: " + Thread.currentThread().getPriority());
            }
        };

        Thread t1 = new Thread(tarea, "uno");
        Thread t2 = new Thread(tarea, "dos");

        t1.start();
        t2.start();

        int terminar;
        boolean turno = false;

        System.out.println(" - - - CONTROL DE PRIORIDADES - - - ");

        do {
            if (turno) {
                t1.setPriority(Thread.MAX_PRIORITY);
                t2.setPriority(Thread.MIN_PRIORITY);
            } else {
                t2.setPriority(Thread.MAX_PRIORITY);
                t1.setPriority(Thread.MIN_PRIORITY);
            }

            // Corregido: Mostrar la prioridad real de t1 y t2, no la del hilo "main"
            System.out.println("Hilo " + Thread.currentThread().getName() +
                    " - Prioridad: " + Thread.currentThread().getPriority());

            turno = !turno;

            // EL SLEEP ESTÁ AHORA EN LA ITERACIÓN DEL MAIN
            // El main se duerme 1 segundo para dejar a los hilos competir en la consola
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Tras 1 segundo de ejecución libre, preguntamos si se detiene
            terminar = JOptionPane.showConfirmDialog(null,
                    "Alternando Prioridades. ¿Deseas detener el programa?",
                    "Control de Hilos",
                    JOptionPane.YES_NO_OPTION);

        } while (terminar != 0);

        // Limpieza al salir
        t1.interrupt();
        t2.interrupt();

        System.out.println(" - - - Finalizando - - - ");
        System.out.println("Fin del MAIN");

        System.exit(0);
    }
}
package Ejercicio011RunStart;

public class MainRunStart {
    public static void main(String[] args) {

        ClaseRun tarea = new ClaseRun();

        System.out.println("---------- Llamando a run() directamente ----------");
        // Esto NO crea un hilo nuevo, simplemente ejecuta run() en el hilo main
        tarea.run();

        System.out.println("---------- Llamando a start() en un Thread ----------");
        // Aquí sí creamos un hilo nuevo
        Thread hilo = new Thread(tarea, "Hilo-RunStart");
        hilo.start();

        System.out.println("main sigue ejecutándose mientras el hilo nuevo trabaja...");
    }
}

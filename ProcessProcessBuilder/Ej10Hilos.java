public class Ej10Hilos {

    public static void main(String[] args) {
        procesosMultiples();
    }

    public static void procesosMultiples() {

        Runnable proceso1 = () -> {
            System.out.println("Iniciando proceso 1: notepad");
            try {
                Process p1 = new ProcessBuilder("cmd", "/c", "start", "/wait", "notepad.exe").start();
                p1.waitFor();
                System.out.println("Notepad cerrado");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Runnable proceso2 = () -> {
            System.out.println("Iniciando proceso 2: calculadora");
            try {
                Process p2 = new ProcessBuilder("cmd", "/c", "start", "/wait", "calc.exe").start();
                p2.waitFor();
                System.out.println("Calculadora cerrada");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Thread hilo1 = new Thread(proceso1);
        Thread hilo2 = new Thread(proceso2);

        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Todos los procesos han terminado.");
    }
}

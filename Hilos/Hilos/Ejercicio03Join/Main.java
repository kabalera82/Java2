package Ejercicio03Join;

public class Main {

    public static void main(String[] args) {

        // 1. Creamos la tarea
        Join mh = new Join();

        // 2. Crear el hilo que ejecutará esa tarea
        Thread th = new Thread(mh);

        // 3. Arranca el hilo
        th.start();

        System.out.println("Main: espero que el trabajador termine con Join");

        try {

            th.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main: yo he termiando");

    }
}

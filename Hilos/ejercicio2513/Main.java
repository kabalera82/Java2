package ejercicio2513;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Nombrado(), "Hilo-1");
        Thread t2 = new Thread(new Nombrado(), "Hilo-2");

        t1.start();
        t2.start();

        // Cambiar prioridades mientras se ejecutan
        for (int i = 0; i < 5; i++) {
            int prioridad1 = 1 + (int) (Math.random() * 10);
            int prioridad2 = 1 + (int) (Math.random()* 10);
            t1.setPriority(prioridad1);
            t2.setPriority(prioridad2);
            Thread.sleep(1000);

            t1.setPriority(Thread.MAX_PRIORITY);
            t2.setPriority(Thread.MIN_PRIORITY);
            Thread.sleep(1000);
        }

        t1.join();
        t2.join();

        System.out.println("Main finalizado");
    }
}

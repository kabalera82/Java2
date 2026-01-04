package ejercicio2215;

public class Ping implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            while (!Main.turno) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println(e.getStackTrace());
                }
            }
            System.out.println(Thread.currentThread().getName());
            Main.turno = false;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e.getStackTrace());
            }
        }

    }
}

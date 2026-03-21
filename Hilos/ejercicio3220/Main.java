package ejercicio3220;

public class Main {

    public static void main(String[] args) {

        Backup backup = new Backup();
        Thread thread = new Thread(backup);

        thread.start();

        do {
            try {
                Thread.sleep(200);
                System.out.println(
                        "Comprobacion en proceso: " + thread.isAlive()
                );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (thread.isAlive());
    }
}
package ejercicio3018;

public class Email implements Runnable {
    @Override
    public void run(){
        try {
            Thread.sleep(1000);
            System.out.println("Bienvenida . . . ");
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }
    }
}

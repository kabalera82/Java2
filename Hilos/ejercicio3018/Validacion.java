package ejercicio3018;

public class Validacion implements Runnable {
    @Override
    public void run(){
        try {
            Thread.sleep(1500);
            System.out.println("Validando . . . ");
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }
    }
}

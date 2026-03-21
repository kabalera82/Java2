package ejercicio3018;

public class Registro implements Runnable{
    @Override
    public void run(){
        try {
            Thread.sleep(2000);
            System.out.println("Registrando . . . ");
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }
    }
}

package ejercicio3018;

public class Carpeta implements Runnable {
    @Override
    public void run(){
        try {
            Thread.sleep(4000);
            System.out.println("Creando carpetas . . . ");
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }
    }
}

package ejercicio2917;

public class HiloB implements Runnable {
    @Override
    public void run(){
        try {

            Thread.sleep(1000);
            System.out.println("Puliendo pieza . . . ");

        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }
    }
}


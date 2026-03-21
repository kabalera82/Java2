package ejercicio2917;

public class HiloC implements Runnable {

    @Override
    public void run(){
        try {
            Thread.sleep(3000);
            System.out.println("Pintando pieza . . . ");

        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }
    }
}



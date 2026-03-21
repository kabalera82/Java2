package ejercicio2917;

public class HiloA implements Runnable {
    @Override
    public void run(){
        try {
            Thread.sleep(2000);
            System.out.println("Fabricando pieza . . . ");
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }
    }
}

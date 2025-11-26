package Ejercicio012Sleep;

public class Tic implements Runnable {

    @Override
    public void run(){

        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(" tic ");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Interrumpido mientras dormia");
            }
        }
    }


}

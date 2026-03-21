package ejercicio2816;

public class Cocinero implements Runnable {

    @Override
    public void run(){
        try {
            int cocinando = (int) ((Math.random() * 3) + 1)*1000;
            Thread.sleep(cocinando);
        } catch (InterruptedException e){
            System.out.println("Hilo semáforo interrumpido");
            Thread.currentThread().interrupt();
        }
    }

}

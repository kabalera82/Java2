package ejercicio2216;

public class Repartidor  implements Runnable{
    @Override
    public void run(){
        try {

            int cocinado = (int) ((Math.random() * 4) + 1)*1000;
            Thread.sleep(cocinado);

        } catch (InterruptedException e){
            System.out.println("Hilo semáforo interrumpido");
            Thread.currentThread().interrupt();
            return;
        }
    }
}

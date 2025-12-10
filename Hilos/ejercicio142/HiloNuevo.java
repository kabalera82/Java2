package ejercicio142;

public class HiloNuevo extends Thread {
    @Override
    public void run(){
        try{
            for(int i = 0;i<5;i++) {
                Thread.sleep(500);
                System.out.println("Tic...");
            }

        } catch (InterruptedException e) {
            System.out.println("Interrumpido");
        }
    }
}

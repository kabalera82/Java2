package ejercicio153;

public class HiloLento extends Thread{

    @Override
    public void run (){
        try {
            System.out.println("Ejecutando hilo " + Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

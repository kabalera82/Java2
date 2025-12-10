package ejercicio131;

public class HiloNum extends Thread {
    @Override
    public void run(){
        System.out.println("Estoy en el hilo " + Thread.currentThread().getName());
        for(int i = 0; i<5; i++){
            System.out.println( i + " " + Thread.currentThread().getName());
        }

    }
}

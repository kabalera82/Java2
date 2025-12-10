package ejercicio131;

public class Ejecutable implements Runnable {
    @Override
    public void run(){
        System.out.println("Estoy en un ejecutable" + Thread.currentThread().getName());
    }
}

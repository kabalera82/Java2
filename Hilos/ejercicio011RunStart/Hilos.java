package Ejercicio011RunStart;

public class Hilos implements Runnable {

    @Override
    public void run(){
        for (int i = 1; i<=5;i++){
            System.out.println("imprime " + i);
        }
    }


}

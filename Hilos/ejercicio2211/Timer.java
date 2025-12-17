package ejercicio2211;

import ejercicio2110.Main;

public class Timer implements Runnable{
 int contador = 0;
    @Override
    public void run(){
        for(int i= 1; i<= 1000; i++){
            if(i % 2 != 0){
                System.out.println(" tic...");
                } else {
                System.out.println(" tac...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}

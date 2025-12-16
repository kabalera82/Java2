package ejercicio208;

import java.awt.print.PrinterException;

public class Pares extends Thread {
    @Override
    public void run(){
        try {
            for (int i = 0; i < 100; i++) {
                if (i % 2 == 0) {
                    System.out.println(" " + i + " ");
                    sleep(200);
                }
            }
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }

    }
}

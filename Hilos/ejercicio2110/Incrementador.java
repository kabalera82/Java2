package ejercicio2110;

public class Incrementador implements Runnable{

    @Override
    public void run(){
        for(int i= 1; i<= 1000; i++){
            Main.contador++;
            if(i % 2 == 0){
                try{
                    Thread.sleep(10);
                    System.out.println("Valor del contador: " + Main.contador);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

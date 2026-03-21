package ejercicio3220;

public class Backup implements Runnable {
    @Override
    public void run(){
        try{
            int max = 7;
            int min = 3;
            for(int i=0;i<10;i++){
                int num = ((int)(Math.random() * (max - min + 1)) + min)*100;
             Thread.sleep(num);
                System.out.println("Backup -> " + i + " <- ");
            }
        } catch (InterruptedException e){
            System.out.println(e);
        }
    }
}

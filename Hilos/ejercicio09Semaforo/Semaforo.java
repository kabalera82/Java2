public class Semaforo implements Runnable{
    @Override
    public void run(){

        for(int i=1; i<=3; i++) {
            try {
                System.out.println("Semáforo en Rojo.");
                Thread.sleep(2000);
                System.out.println("Semáforo en Amarillo");
                Thread.sleep(3000);
                System.out.println("Semáforo en Verde");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

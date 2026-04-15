public class MiHilo implements Runnable{

    @Override
    public void run(){
        try {
            for (int i=1; i<=5;i++) {
                Thread.sleep(500);
                System.out.println("tic");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
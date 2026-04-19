public class Par implements Runnable{
    @Override
    public void run(){

        for(int i=0; i<=20; i+=2){

            try {
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + " " + i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
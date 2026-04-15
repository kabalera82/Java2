public class Join implements Runnable{

    @Override
    public void run(){
     try{
         Thread.sleep(2000);
     } catch (InterruptedException e) {
         throw new RuntimeException(e);
     }
    }
}

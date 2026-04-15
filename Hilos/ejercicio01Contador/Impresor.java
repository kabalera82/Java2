public class Impresor extends Thread{

    @Override
    public void run(){
        for (int i=1; i<=5;i++){
            System.out.println(Thread.currentThread().getName() + " imprime " + i);
        }
    }
}

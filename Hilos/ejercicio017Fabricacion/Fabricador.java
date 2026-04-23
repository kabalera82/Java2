
public class Fabricador implements Runnable {

    @Override
    public void run () {

        try {

            System.out.println("Comenzando a fabricar la " + Thread.currentThread().getName());
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("fabricacion completada");
    }
}

public class Validar implements Runnable {

    @Override
    public void run () {

        try {
            System.out.println("Validando" + Thread.currentThread().getName());
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Validado");
    }
}

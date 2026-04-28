public class Carpeta implements Runnable {

    @Override
    public void run () {

        try {

            System.out.println("Creando carpeta " + Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("fabricacion completada");
    }
}

public class Cocinero implements Runnable {

    /* cocinero: tarda entre 1 y 3 segundos en “preparar un pedido”.*/
    @Override
    public void run (){
        // Quitamos el 'public' y corregimos la fórmula matemática
        long cocinado = (long) (Math.random() * 2000) + 1000;

        try {
            System.out.println("Cocinero " + Thread.currentThread().getName() + " está cocinando (" + cocinado + " ms)");
            Thread.sleep(cocinado);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
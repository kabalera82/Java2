public class Repartidor implements Runnable {

    /* REPARTIDOR: tarda entre 2 y 4 segundos en “repartirlo”.*/
    @Override
    public void run (){
        // Cambiamos double a long y multiplicamos por miles para obtener milisegundos
        long reparto = (long) (Math.random() * 2000) + 2000;

        try{
            System.out.println("Pedido de " + Thread.currentThread().getName() + " en reparto (" + reparto + " ms)");
            Thread.sleep(reparto);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
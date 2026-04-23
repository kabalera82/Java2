
public class Pulidor implements Runnable{

    // Variable para guardar el hilo
    private Thread hiloQueEspera;

    // constructor
    public Pulidor (Thread hilo){
        this.hiloQueEspera = hilo;
    }
    @Override
    public void run () {

        try {
            hiloQueEspera.join();
            System.out.println("Comenzando a pulir " + Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Pulido completado");
    }

}

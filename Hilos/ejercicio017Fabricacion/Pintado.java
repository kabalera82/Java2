public class Pintado implements Runnable{

    // vARIABLE PARA GUARDAR EL HILO A ESPERAR
    private Thread hiloQueEspera;

    // contructor
    public Pintado (Thread hilo) {
        this.hiloQueEspera = hilo;
    }

    @Override
    public void run() {


        try{
            hiloQueEspera.join();
            System.out.println("comenzando a pintar " + Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println(" Pintado completado");
        } catch (InterruptedException e){
            throw new RuntimeException (e);
        }


    }

}

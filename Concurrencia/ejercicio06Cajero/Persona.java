package ejercicio06Cajero;

public class Persona implements Runnable {

    private final Cajero cajero;
    private int cantidad;
    int min = 20;
    int max = 200;

    public Persona(Cajero cajero) {
        this.cajero = cajero;
    }

    @Override
    public void run(){
        try {
            while(true){
                cantidad = (int) (Math.random() * ((max - min) + 1)) + min;
                boolean retira = cajero.reintegro(cantidad);

                if(!retira) break;

                System.out.println("retirando efectivo . . . ");
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

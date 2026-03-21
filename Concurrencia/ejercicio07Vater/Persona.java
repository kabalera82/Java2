package ejercicio07Vater;

public class Persona implements Runnable {

    private final Vater vater;

    public Persona(Vater vater){
        this.vater = vater;
    }

    @Override
    public void run(){

        try {
            // Cada persona llega en un momento aleatorio
            Thread.sleep((int)(Math.random() * 10) * 1000);
        } catch (InterruptedException e) {
            return;
        }

        // Intenta entrar al baño
        vater.orinando();

        try {
            // Tiempo usando el baño
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return;
        }

        // Sale del baño
        vater.sacudida();
    }
}

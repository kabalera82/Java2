
public class Amigo implements Runnable {

    private String nombre;

    public Amigo(String nombre){
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try{
            System.out.println(nombre+ " ha empezado . . . ");
            Thread.sleep(3000);
            System.out.println(nombre + " ha terminado.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

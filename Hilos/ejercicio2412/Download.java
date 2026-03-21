package ejercicio2412;

public class Download implements Runnable{
    @Override
    public void run() {
        String nombre = Thread.currentThread().getName();
        try {
            System.out.println(nombre + " → inicia descarga");
            Thread.sleep(1000 + (int) (Math.random() * 4000));
            System.out.println(nombre + " → descarga finalizada");
        } catch (InterruptedException e) {
            System.out.println(nombre + " → descarga interrumpida");
        }
    }
}

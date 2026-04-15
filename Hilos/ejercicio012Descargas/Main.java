
/**
 12. Ejercicio: Descargar archivos simulados
 Simula 3 descargas:
 Cada descarga es un hilo.
 Cada hilo tarda entre 1 y 5 segundos (sleep).
 El hilo main NO puede continuar hasta que todas las descargas terminen → join()
 sobre cada una.
 */


public class Main {

    public static void main(String[] args) {


        Runnable descarga = () -> {
            try {
                int sleeper = (int) (2000 * Math.random());
                System.out.println("Descargando "+Thread.currentThread().getName() );
                Thread.sleep(sleeper);
                System.out.println("Descarga de " + Thread.currentThread().getName() + " finalizada");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread t1 = new Thread(descarga,(" . . . Musica . . . "));
        Thread t2 = new Thread(descarga, (" . . . Video . . . "));
        Thread t3 = new Thread(descarga,("  . . . Imagenes . . . "));

        try {
            t1.start();t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
            System.out.println(Thread.currentThread().getName() + " finalizado.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}

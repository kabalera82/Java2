/**
 * 10. Ejercicio: Contador compartido sin sincronización
 * Crea 2 hilos que incrementen el mismo contador 1000 veces.
 * Usa sleep(1) en algunas vueltas.
 * Observa resultados distintos cada ejecución → condición de carrera.
 * Explica por qué pasa.
 */

public class Main {

    //El contador debe ser static para que la caja sea unica
    static int contador;

    public static void main(String[] args) {

        Runnable Tarea = () -> {

            for(int i=1; i<= 1000; i++){
                contador++;
                try{
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() +" "+ contador);
            }
        };

        Thread t1 = new Thread((Tarea), "Trabajador - 1");
        Thread t2 = new Thread((Tarea), "Trabajador - 2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }




    }

}

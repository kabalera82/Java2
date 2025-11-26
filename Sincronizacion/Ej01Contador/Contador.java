package Ej01Contador;

// La clase Contador implementa Runnable para poder ser usada por hilos
public class Contador implements Runnable {

    // Variable compartida que van a modificar los dos hilos
    private int contador = 0;

    // Método que ejecutará cada hilo cuando se llame a start() sobre Thread
    @Override
    public void run() {
        // Cada hilo incrementa el contador 5 veces
        for (int i = 0; i < 5; i++) {
            incrementar();
        }
    }

    // Método sincronizado: solo un hilo puede ejecutar este método a la vez
    public synchronized void incrementar() {
        // Leemos el valor actual del contador
        int v = contador;
        System.out.println(Thread.currentThread().getName() + " leyendo " + v);

        try {
            // Simulamos un pequeño retardo para hacer más visible la concurrencia
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // Si el hilo es interrumpido durante el sleep, lanzamos una RuntimeException
            throw new RuntimeException(e);
        }

        // Actualizamos el valor del contador sumando 1
        contador = v + 1;
        System.out.println(Thread.currentThread().getName() + " escribiendo: " + contador);
    }

    // Getter para obtener el valor actual del contador
    public int getValor() {
        return contador;
    }
}

package ejercicio164;

/**
 * Crea dos hilos:
 * Hilo A tarda 3 segundos en terminar.
 * Hilo B no puede comenzar hasta que A haya terminado → usa join().
 */

/*
SIN join():
-------------------------
main -> sigue ejecutándose sin esperar
h1   -> se ejecuta en paralelo
h2   -> se ejecuta en paralelo

=> No hay orden garantizado entre h1 y h2
=> B puede empezar antes que A
*/


/*
CON join():
-------------------------
main -> llama a h1.join() y queda en estado WAITING (bloqueado)
h1   -> se ejecuta y termina
main -> es notificado de que h1 terminó y continúa su ejecución
h2   -> se inicia después de que h1 ha acabado

=> B solo empieza cuando A ha terminado
=> join garantiza el orden
*/

public class Main {

    public static void main(String[] args) {

        // Creamos dos hilos del mismo tipo
        Hilo1 h1 = new Hilo1();   // Hilo A
        Hilo1 h2 = new Hilo1();   // Hilo B

        // Imprime el nombre del hilo actual (main)
        System.out.println(Thread.currentThread().getName());

        // Arrancamos el hilo h1 (A)
        h1.start();

        // El hilo main sigue ejecutándose inmediatamente
        System.out.println(Thread.currentThread().getName());

        try {
            // Antes del join, sigue estando en el hilo main
            System.out.println(Thread.currentThread().getName());

            // main se BLOQUEA hasta que h1 termine
            h1.join();

            // Cuando h1 acaba, main vuelve a ejecutarse
            System.out.println(Thread.currentThread().getName());

            // Ahora arrancamos h2 SOLO cuando h1 ya ha terminado
            h2.start();

            // Esperamos también a que h2 termine
            h2.join();

            // Cuando h2 acaba, seguimos ejecutando main
            System.out.println(Thread.currentThread().getName());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Hilo1 extends Thread {

        @Override
        public void run() {
            try {
                // Indica que el hilo está empezando
                System.out.println("Ejecutándose " + Thread.currentThread().getName());

                // Simula trabajo del hilo → 3 segundos
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

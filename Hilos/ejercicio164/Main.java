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

        Hilo1 h1 = new Hilo1();
        Hilo1 h2 = new Hilo1();

        System.out.println(Thread.currentThread().getName());
        h1.start();
        System.out.println(Thread.currentThread().getName());
        try {
            System.out.println(Thread.currentThread().getName());
            h1.join();
            System.out.println(Thread.currentThread().getName());
            h2.start();
            h2.join();
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public static class Hilo1 extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("Ejecutandose" + Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}

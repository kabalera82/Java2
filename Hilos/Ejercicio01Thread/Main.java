package Ejercicio01Thread;

import Ejercicio01Thread.MiHilo;


/**
 * Crea un programa que:
 * •
 * Tenga un hilo que imprime números del 1 al 5.
 * •
 * Llama a run() primero.
 * •
 * Luego llama a start().
 * •
 * Observa el orden de ejecución.
 * •
 * Explica por qué ocurre.
 */
public class Main {

    public static void main(String[] args) {

        /*
        MiHilo miHilo = new MiHilo();
        miHilo.start();


        EjemploRunnable er = new EjemploRunnable();

        System.out.println("Llamando a run () directamnte")
        er.run();

        System.out.println("Llamando a start()");
        Thread hilo = new Thread(er);
        hilo.start();

        System.out.println(" Fin del main, hilo = " + Thread.currentThread().getName()) ;

        .run()
        .start()
        .sleep(long milis) -> metodo stático de thread y va a pausar el hilo en milisegundos
            - hay que prestar atencion a interrupted exception.
            - pausa el hilo si quieres 1 seg hay que poner 1000
        .join() -> Sirve para que un hilo Espere a que un hilo termine para evitar abrazo de la muerte etc
        .setPriority(int newPriority) -> sirve para cambiar la prioridad de un Hilo (no garantiza que se ejecute antes)
            - Thread.MIN_PRIORITY -> 1 - Thread.MAX_PRIORITY -> 10. POR DEFECTO: Thread.NORM_PRIORITY-> 5
        .isAlive() -> devuelve true si el hilo ha sido iniciado con start y todavia no ha terminado su ejecucion
            -> devuelve false si aun no lo ha llamado start()


         */
    }
}

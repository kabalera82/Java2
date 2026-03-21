package ejercicio2715;

/**
 * 15. Ejercicio: Dos hilos que deben alternarse
 * Hilo A imprime “PING”.
 * Hilo B imprime “PONG”.
 * Deben alternarse con un sleep(500).
 * No deben imprimir dos PING seguidos ni dos PONG seguidos
 * (versión simple: usa flags compartidas sin synchronized)
 */
public class Main {
    static boolean turno = true;

    public static void main(String[] args) {


        Ping pi = new Ping();
        Pong po = new Pong();

        Thread ti = new Thread(pi, "Ping");
        Thread to = new Thread(po, "Pong");

        ti.start();
        to.start();



    }
}
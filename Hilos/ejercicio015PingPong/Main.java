public class Main {

    // volatile garantiza que ambos hilos ven el valor actualizado
    private static volatile boolean turno = true; // true = PING, false = PONG

    public static void main(String[] args) {

        // Creamos el hilo
        Thread ping = new Thread(() -> {
            // mientras sea cierto
            while (true) {
                // condicion if con el boolean
                if (turno) {
                    // imprimimos el nombre del hilo
                    System.out.println(" - - - " + Thread.currentThread().getName() +" - - - ");
                    // dormimor el hilo y controlamos la excepcion por interrupcion
                    try { Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // salimos del bucle
                        return;
                    }
                    turno = false; // Cedo el turno DESPUÉS de imprimir
                }
            }
        }, "PING");

        Thread pong = new Thread(() -> {
            while (true) {
                if (!turno) {
                    System.out.println("PONG");
                    try { Thread.sleep(500); } catch (InterruptedException e) { return; }
                    turno = true; // Cedo el turno DESPUÉS de imprimir
                }
            }
        }, "PONG");

        ping.start();
        pong.start();
    }
}
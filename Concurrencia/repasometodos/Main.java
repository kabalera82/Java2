package repasometodos;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Object monitor = new Object();

        // Creamos 3 hilos que esperan
        Thread e1 = new Thread(new HiloEsperador(monitor), "Hilo-1");
        Thread e2 = new Thread(new HiloEsperador(monitor), "Hilo-2");
        Thread e3 = new Thread(new HiloEsperador(monitor), "Hilo-3");

        e1.start();
        e2.start();
        e3.start();

        // Damos tiempo para que todos hagan wait()
        Thread.sleep(1000);

        // PROBAR UN SOLO NOTIFY()
        Thread notificador1 = new Thread(new HiloNotificador(monitor), "Notificador-UNO");
        notificador1.start();

        // Esperamos para ver el efecto de notify()
        Thread.sleep(1000);

        // PROBAR NOTIFYALL()
        Thread notificadorTodos = new Thread(new HiloNotificadorTodos(monitor), "Notificador-TODOS");
        notificadorTodos.start();

    }
}

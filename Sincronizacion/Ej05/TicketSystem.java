package Ej05;

public class TicketSystem {

    private int tickets = 20;
    private final MiLock lock = new MiLock();

    public boolean comprarTicket(String cliente) {

        boolean comprado = false;

        try {
            lock.lock(); // Entramos en la sección crítica

            if (tickets > 0) {
                System.out.println(cliente + " ha comprado un ticket. Quedan: " + (tickets - 1));
                tickets--;
                comprado = true;
            }

        } catch (InterruptedException e) {
            // Restaurar el estado de interrupción del hilo
            Thread.currentThread().interrupt();
        } finally {
            // Muy importante: liberar siempre el lock
            lock.unlock();
        }

        return comprado;
    }
}

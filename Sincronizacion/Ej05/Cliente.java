package Ej05;

public class Cliente implements Runnable {

    private final String nombre;
    private final TicketSystem ticketSystem;

    public Cliente(String nombre, TicketSystem ticketSystem) {
        this.nombre = nombre;
        this.ticketSystem = ticketSystem;
    }

    @Override
    public void run() {

        boolean comprado = true;

        // Intentamos comprar tickets hasta que ya no queden
        while (comprado) {
            try {
                Thread.sleep(100); // pequeña pausa para ver mejor la salida
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println(nombre + " no ha podido comprar más tickets.");
    }
}

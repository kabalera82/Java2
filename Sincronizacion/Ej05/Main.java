package Ej05;

public class Main {

    public static void main(String[] args) {

        TicketSystem ticketSystem = new TicketSystem();

        // Creamos varios clientes (hilos)
        Cliente c1 = new Cliente(system,"Cliente 1");
        Cliente c2 = new Cliente(system,"Cliente 2");
        Cliente c3 = new Cliente(system,"Cliente 3");
        Cliente c4 = new Cliente(system,"Cliente 4");

        // Los arrancamos
        c1.start();
        c2.start();
        c3.start();
        c4.start();
    }
}

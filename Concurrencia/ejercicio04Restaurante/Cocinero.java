package ejercicio04Restaurante;

public class Cocinero implements Runnable{

    private Cocina cocina;
    private int id;

    public Cocinero(int id, Cocina cocina){
        this.id = id;
        this.cocina = cocina;
    }

    @Override
    public void run() {

        // Consumidor infinito: siempre intenta retirar pedidos y "cocinarlos".
        while (true){

            // Retira un pedido del buffer compartido.
            // - Si la cola está vacía, este hilo se bloqueará dentro de retirarPedido() con wait().
            // - Si hay pedidos, saca uno (FIFO) y continúa.
            String pedido = cocina.retirarPedido();

            try {
                System.out.println("Cocinero "+id+" preparando "+pedido);

                // Simulación del tiempo de cocina.
                // Importante: esto ocurre FUERA del synchronized de Cocina,
                // así que no bloqueas a otros hilos mientras "cocinas".
                Thread.sleep(1000);

                System.out.println("Cocinero: "+id+" ha terminado "+pedido);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}

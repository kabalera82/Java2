package ejercicio04Restaurante;

public class Camarero implements Runnable {
    private Cocina cocina;
    private int id;
    public Camarero(int id, Cocina cocina) {
        this.id = id;
        this.cocina = cocina;
    }
    @Override
    public void run() {
        // Contador local del hilo: no hay concurrencia aquí porque cada camarero tiene su propio contador.
        int contadorPedidos = 1;
        // Productor infinito: genera pedidos y los mete en la cocina (buffer).
        while (true) {
            // Construye el pedido.
            String pedido = "Pedido " + contadorPedidos + " (Camarero " + id + " )";
            // Intenta añadir al buffer:
            // - Si está lleno, se bloqueará dentro de aniadirPedido() con wait().
            // - Si hay hueco, lo añade y despierta a los cocineros (notifyAll).
            cocina.aniadirPedido(pedido);
            contadorPedidos++;
            try {
                // Simula el ritmo de producción de pedidos.
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

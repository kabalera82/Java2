package Restaurante;

public class Camarero implements Runnable {
    private Cocina cocina;
    private int id;

    public Camarero(Cocina cocina, int id) {
        this.cocina = cocina;
        this.id = id;
    }

    @Override
    public void run(){
        int contadorPedidos = 1;
        while (true){
            String pedido = "Pedido " + contadorPedidos + " (Camarero " + id + ")";
            cocina.addPedido(pedido);
            contadorPedidos++;

            try {
                Thread.sleep(500); // Pequeña pausa entre pedidos
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
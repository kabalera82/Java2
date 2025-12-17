package Restaurante;

public class Cocinero implements Runnable{
    private Cocina cocina;
    private int id;

    public Cocinero(int id, Cocina cocina) {
        this.cocina = cocina;
        this.id = id;
    }

    @Override
    public void run(){
        while (true){
            String pedido = cocina.retirarPedido();
            try {
                System.out.println("Cocinero " + id + " preparando pedido: " + pedido);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
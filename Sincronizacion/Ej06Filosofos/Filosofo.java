package Ej06Filosofos;

public class Filosofo implements Runnable {

    private Mesa mesa;
    private int comensal;
    private int indiceComensal;
    public Filosofo(Mesa mesa, int comensal) {
        this.mesa = mesa;
        this.comensal = comensal;
        this.indiceComensal = comensal - 1;
    }

    private void pensando(){
        System.out.println("Filosofo " + comensal + "esta pensado.");
        try{
            Thread.sleep((int) (Math.random() * 4000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void comiendo(){
        System.out.println("Filosofo " + comensal + "esta comiendo.");
        try{
            Thread.sleep((int) (Math.random() * 4000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while(true){
            pensando();

            mesa.cogerTenedores(indiceComensal);

            comiendo();

            System.out.println("Filosofo "+comensal+" deja de comer, tenedores libres "+ (mesa.tenedorIzquierda(indiceComensal)+1)+", "+(mesa.tenedorDerecha(indiceComensal)+1));

            mesa.dejarTenedores(indiceComensal);
        }
    }
}

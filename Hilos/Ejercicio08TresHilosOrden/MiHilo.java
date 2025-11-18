package Ejercicio08TresHilosOrden;

public class MiHilo implements Runnable {
    public String mensaje;
    public MiHilo(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void run () {
        System.out.println(mensaje);
    }
}

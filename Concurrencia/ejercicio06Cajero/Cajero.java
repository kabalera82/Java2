package ejercicio06Cajero;

public class Cajero {

    private static int saldo = 2000;

    public synchronized boolean reintegro(int cantidad) {

        // Si no alcanza el saldo, NO se puede retirar
        if (cantidad > saldo) {
            System.out.println(Thread.currentThread().getName()
                    + " intenta retirar " + cantidad + "€ -> NO HAY SALDO SUFICIENTE");
            return false;
        }

        // Operación indivisible: comprobar + restar
        saldo -= cantidad;

        System.out.println(Thread.currentThread().getName()
                + " retira " + cantidad + "€ -> saldo: " + saldo);

        return true;
    }

    public int getSaldo() {
        return saldo;
    }
}

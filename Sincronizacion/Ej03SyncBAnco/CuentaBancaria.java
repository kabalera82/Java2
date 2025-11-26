package Ej03SyncBAnco;

// Clase que representa una cuenta bancaria con un saldo entero.
// Varios hilos (cajeros) accederán a esta misma cuenta a la vez.
public class CuentaBancaria {
    // Saldo actual de la cuenta
    private int saldo;

    // Constructor: inicializa la cuenta con un saldo inicial
    public CuentaBancaria(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    // Método sincronizado para retirar dinero de la cuenta.
    // synchronized garantiza que solo un hilo puede ejecutar este método
    // a la vez sobre el mismo objeto CuentaBancaria.
    public synchronized void retirar(int cantidad){
        // Nombre del hilo que está intentando retirar
        String hilo = Thread.currentThread().getName();
        // Comprobamos si hay saldo suficiente para retirar la cantidad
        if(saldo >= cantidad){
            System.out.println(hilo + " va a retirar " + cantidad+ " €");
            try {
                // Pausa para simular el tiempo de proceso de la operación
                Thread.sleep(300);
            } catch (InterruptedException e) {
                // Si el hilo es interrumpido durante la pausa, se lanza RuntimeException
                throw new RuntimeException(e);
            }
            // Actualizamos el saldo restando la cantidad retirada
            saldo -= cantidad;
            System.out.println(hilo+ "ha retirado "+ cantidad+ "€. Saldo restante: " +saldo);
        } else{
            // Mensaje si no hay suficiente saldo para realizar la retirada
            System.out.println("NO PUEDE RETIRAR " +cantidad+" SALDO INSUFICIENTE ("+saldo+"€).");
        }
    }
}

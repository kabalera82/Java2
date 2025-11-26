package Ej03SyncBAnco;

import java.util.Random;

// Clase que representa un cajero automático que va a ejecutar un hilo.
// Cada cajero hará varias retiradas de la misma cuenta bancaria compartida.
public class Cajero implements Runnable{

    // Referencia a la cuenta bancaria sobre la que opera este cajero
    private CuentaBancaria cuenta;
    // Objeto para generar números aleatorios (cantidades y pausas)
    private Random random = new Random();

    // Constructor: recibe la cuenta bancaria que va a usar este cajero
    public Cajero (CuentaBancaria cuenta){
        this.cuenta = cuenta;
    }


    @Override
    public void run(){
        // El cajero realiza 5 intentos de retirada
        for(int i = 0;i<5;i++){
            // Genera una cantidad aleatoria entre 100 y 500 euros
            int cantidad = random.nextInt(401)+100; // 100 - 500;
            // Intenta retirar esa cantidad de la cuenta compartida
            cuenta.retirar(cantidad);
            try {
                // Pausa aleatoria entre 300 y 800 ms para simular tiempos reales
                Thread.sleep(random.nextInt(500) + 300);
            } catch (InterruptedException e) {
                // Si el hilo es interrumpido, se lanza una RuntimeException
                throw new RuntimeException(e);
            }
        }
    }
}

package Restaurante;

/**
 * Gestionar varios estados y sincronización mas compleja
 *
 * Simula un restaurante con:
 * Camareros (productors de pedidos)
 * Cocineros (consumidores de pedidos)
 *
 * Condiciones:
 * Máximo 10 pedidos en la cocina
 *
 * Si la cocina está llena -> camareros espera -> wait()
 * si no hay pedido -> cocineros esperan  -> wait()
 *
 * Pedido añadido o retirado -> notifyAll()
 */
public class Main {
    public static void main(String[] args) {
        Cocina cocina = new Cocina();

        // Crear 3 camareros
        for (int i = 1; i <= 10; i++) {
            Thread camarero = new Thread(new Camarero(cocina, i));
            camarero.start();
        }

        // Crear 2 cocineros
        for (int i = 1; i <= 5; i++) {
            Thread cocinero = new Thread(new Cocinero(i, cocina));
            cocinero.start();
        }
    }
}


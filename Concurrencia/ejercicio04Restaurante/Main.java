package ejercicio04Restaurante;

/**
 * Gestionar varios estados y sincronización más compleja
 *
 * Simula un restaurante con:
 * - Camareros (productores de pedidos)
 * - Cocineros (consumidores de pedidos)
 *
 * Condiciones:
 *
 * Máximo 10 pedidos en cocina.
 *
 * Si la cocina está llena -> camareros esperan -> wait()
 * Si no hay pedidos -> cocineros esperan -> wait()
 *
 * Pedido añadido o retirado -> notifyAll()
 *
 * Requisitos:
 *
 * Clase Cocina como recurso compartido
 *
 * Uso obligatorio de wait() y notifyAll()
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

        // Recurso compartido ÚNICO: contiene la cola de pedidos y el monitor (lock) que sincroniza a todos.
        // Todos los camareros y cocineros comparten esta misma instancia.
        Cocina cocina = new Cocina();

        // Productores: generan pedidos y los intentan meter en la cola (buffer).
        Camarero c1 = new Camarero(1, cocina);
        Camarero c2 = new Camarero(2, cocina);

        // Consumidores: sacan pedidos de la cola y simulan cocinarlos.
        Cocinero k1 = new Cocinero(1, cocina);
        Cocinero k2 = new Cocinero(2, cocina);

        // Cada Runnable se ejecuta en su propio hilo real.
        Thread tc1 = new Thread(c1);
        Thread tc2 = new Thread(c2);
        Thread tk1 = new Thread(k1);
        Thread tk2 = new Thread(k2);

        // Arranque concurrente: a partir de aquí, productores y consumidores compiten por el lock de Cocina.
        tc1.start();
        tc2.start();
        tk1.start();
        tk2.start();

        // Nota: el programa no termina nunca porque los run() de Camarero y Cocinero tienen while(true).
    }
}

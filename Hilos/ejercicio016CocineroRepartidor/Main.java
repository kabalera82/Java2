
/**
 16. Ejercicio: Simulador de cocinero y repartidor
 Crea dos hilos:
 COCINERO: tarda entre 1 y 3 segundos en “preparar un pedido”.
 REPARTIDOR: tarda entre 2 y 4 segundos en “repartirlo”.
 El main debe controlar:
 El cocinero empieza.
 Cuando termine, start del repartidor → join.
 Repite 5 pedidos.
 Usa isAlive() para mostrar estados
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("👨‍🍳 ¡Abriendo el restaurante! Preparando 5 pedidos...\n");

        // Repetimos 5 pedidos
        for (int i = 1; i <= 5; i++) {
            System.out.println(" - - - PEDIDO: " + i + " - - - ");

            Thread t1 = new Thread(new Cocinero(), "Cocinero");
            Thread t2 = new Thread(new Repartidor(), "Repartidor");

            // Mostramos estado ANTES de empezar
            System.out.println("Estado de: ¿" + t1.getName() + " está vivo antes de start?: " + t1.isAlive());

            // El cocinero empieza
            t1.start();
            System.out.println("Estado de: ¿" + t1.getName() + " está vivo trabajando?: " + t1.isAlive());

            // El hilo PRINCIPAL (main) espera a que el cocinero termine
            t1.join();

            // Si el código llega aquí, es porque el cocinero ya terminó
            System.out.println("[Estado] ¿" + t1.getName() + " está vivo tras terminar?: " + t1.isAlive());
            System.out.println("--> 🍲 Comida lista, turno del repartidor.");

            // El repartidor empieza
            System.out.println("[Estado] ¿" + t2.getName() + " está vivo antes de start?: " + t2.isAlive());
            t2.start();
            System.out.println("[Estado] ¿" + t2.getName() + " está vivo trabajando?: " + t2.isAlive());

            // El hilo PRINCIPAL espera a que el repartidor entregue el pedido
            t2.join();

            // Si el código llega aquí, el pedido fue entregado
            System.out.println("[Estado] ¿" + t2.getName() + " está vivo tras repartir?: " + t2.isAlive());

            System.out.println("✅ Pedido #" + i + " completado con éxito.\n");
        }

        System.out.println("🏁 Se han repartido los 5 pedidos. ¡Fin de la jornada!");
    }
}

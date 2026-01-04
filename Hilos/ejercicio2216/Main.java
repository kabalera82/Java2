package ejercicio2216;

/**
 * 16. Ejercicio: Simulador de cocinero y repartidor
 * Crea dos hilos:
 * COCINERO: tarda entre 1 y 3 segundos en “preparar un pedido”.
 * REPARTIDOR: tarda entre 2 y 4 segundos en “repartirlo”.
 * El main debe controlar:
 * El cocinero empieza.
 * Cuando termine, start del repartidor → join.
 * Repite 5 pedidos.
 * Usa isAlive() para mostrar estados.
 */

public class Main {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {  // Bucle correcto, sin punto y coma
            System.out.println("Pedido " + i + ": Iniciando cocinero...");

            Cocinero c1 = new Cocinero();
            Repartidor r1 = new Repartidor();

            Thread h1 = new Thread(c1, "Cocinero");
            Thread h2 = new Thread(r1, "Repartidor");

            try {
                h1.start();
                System.out.println(h1.getName() + " trabajando: " + h1.isAlive());
                while (h1.isAlive()) {
                    System.out.println("Pedido " + i + " cocinándose...");
                    Thread.sleep(500);
                }
                h1.join();
                System.out.println(h1.getName() + " trabajando: " + h1.isAlive() + " pedido " + i + " terminado");

                h2.start();
                System.out.println(h2.getName() + " trabajando: " + h2.isAlive());
                while (h2.isAlive()) {
                    System.out.println("Pedido " + i + " entregándose...");
                    Thread.sleep(500);
                }
                h2.join();
                System.out.println(h2.getName() + " entregandose: " + h2.isAlive()+ " pedido " + i + " entregado");
                System.out.println("Pedido " + i + " completado.\n");

            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido");
            }
        }
    }
}
package ejercicio04Restaurante;

import java.util.LinkedList;
import java.util.Queue;

public class Cocina {

    // Cola de pedidos (recurso compartido).
    // FIFO: el primer pedido en entrar es el primero en cocinarse.
    private Queue<String> pedidos = new LinkedList<>();

    // Capacidad máxima del buffer: como mucho 10 pedidos "en cocina".
    private final int CAPACIDAD_MAX = 10;

    public synchronized void aniadirPedido(String pedido){

        // Productor (camarero) intenta añadir.
        // Estado A: cocina llena -> no se puede añadir.
        // while (NO if) porque al despertar se debe revalidar la condición
        // (por spurious wakeups o porque otro hilo se adelantó).
        while(pedidos.size() == CAPACIDAD_MAX){
            System.out.println("Cocina llena. Camarero esperando...");
            try {
                // wait() libera el lock de Cocina y duerme el hilo hasta notifyAll().
                // Mientras espera, otros hilos pueden entrar a retirarPedido() y liberar espacio.
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Sección crítica: modifica el recurso compartido.
        pedidos.add(pedido);
        System.out.println("Pedido añadido: "+pedido+ "| Pedidos en cocina: "+pedidos.size());

        // Cambio de estado: ahora puede haber cocineros esperando "cola vacía".
        // notifyAll (no notify) porque hay dos condiciones distintas (llena/vacía) y no controlas a quién despiertas.
        notifyAll();

    }

    public synchronized String retirarPedido(){

        // Consumidor (cocinero) intenta retirar.
        // Estado B: cola vacía -> no se puede retirar.
        // while (NO if) para revalidar la condición al despertar.
        while (pedidos.isEmpty()){
            System.out.println("No hay pedidos. Cocinero esperando...");
            try {
                // wait() libera el lock de Cocina y duerme el hilo hasta notifyAll().
                // Mientras espera, otros hilos pueden entrar a aniadirPedido() y meter pedidos.
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Sección crítica: consume realmente del recurso compartido.
        // poll() devuelve y elimina el primer elemento (FIFO).
        String pedido = pedidos.poll();
        System.out.println("Cocinando pedido: "+pedido+" | Pedidos restantes: "+pedidos.size());

        // Cambio de estado: ahora puede haber camareros esperando "cocina llena".
        notifyAll();

        return pedido;

    }

}

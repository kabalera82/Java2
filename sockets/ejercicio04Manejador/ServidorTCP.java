package ejercicio04Manejador;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP Mutli-cliente (con hilos)
 * Objetivo: servidor sea capaz de entender varios clientes a la vez (uno por hilo)
 * <p>
 * Checklist:
 * Servidor en bucle infinito aceptando clientes
 * Por cada accept(), crea un Thread o Runnable
 * El hilo va a gestionar la comunicación con ese cliente
 * logs: "Cliente conectado", "cliente desconectado"
 * El servidor no se bloquee con un solo cliente.
 */


public class ServidorTCP {


    public static void main(String[] args) {

        final String HOST = "127.0.0.1";
        final int PUERTO = 5000;

        System.out.println("-------- CLIENTE ---------");

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor MULTICLIENTE iniciado en puerto " + PUERTO);

            while (true) {
                //Esperando a que llegue el cliente
                Socket sc = servidor.accept();
                System.out.println("Cliente conectado: " + sc.getInetAddress() + ":" + sc.getPort());

                //Creamos un hilo por cliente
                Thread hilo = new Thread(new ManejadorCliente(sc));
                hilo.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
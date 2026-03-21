package ejercicio02;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Servidor {

    /**
     * 2) TCP Multi-mensaje: “Conversación hasta ‘salir’”
     * Objetivo: mantener conexión abierta y mandar varios mensajes hasta escribir salir.
     * Checklist
     *  Usar bucle en cliente y servidor
     *  Si mensaje == "salir" → cerrar conexión
     *  Cada mensaje del cliente debe generar respuesta del servidor
     *  Evitar bloqueos (orden correcto: enviar/recibir)
     */
    public static void main(String[] args) {
        final int PUERTO = 5001; // Puerto diferente al ejemplo anterior
        ServerSocket servidor = null;
        Socket socketCliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;
        Scanner teclado = null;

        System.out.println("=== SERVIDOR CHAT TCP ===");
        System.out.println("Mensaje 'salir' del cliente cierra la conexión");

        try {
            // 1. Crear ServerSocket
            servidor = new ServerSocket(PUERTO);
            System.out.println("✅ Servidor iniciado en puerto " + PUERTO);
            System.out.println("⏳ Esperando cliente...");

            // 2. Aceptar conexión
            socketCliente = servidor.accept();
            System.out.println("✅ Cliente conectado: " +
                    socketCliente.getInetAddress().getHostAddress());
            System.out.println("\n--- INICIA CONVERSACIÓN ---");

            // 3. Configurar streams
            entrada = new BufferedReader(
                    new InputStreamReader(socketCliente.getInputStream()));
            salida = new PrintWriter(socketCliente.getOutputStream(), true);
            teclado = new Scanner(System.in);

            // 4. Bucle principal de conversación
            boolean continuar = true;
            while (continuar) {
                // A. Recibir mensaje del cliente
                System.out.print("\n[Esperando cliente]... ");
                String mensajeCliente = entrada.readLine();

                if (mensajeCliente == null) {
                    System.out.println("⚠️  Cliente cerró la conexión abruptamente.");
                    break;
                }

                System.out.println("Cliente: " + mensajeCliente);

                // B. Verificar si es comando de salida
                if (mensajeCliente.equalsIgnoreCase("salir")) {
                    System.out.println("🚪 Cliente solicitó terminar conexión.");
                    continuar = false;
                    // Enviar confirmación de despedida
                    salida.println("Adiós. Conexión cerrada.");
                } else {
                    // C. Pedir respuesta al usuario (servidor)
                    System.out.print("Tú (respuesta): ");
                    String respuesta = teclado.nextLine();

                    // D. Enviar respuesta al cliente
                    salida.println(respuesta);

                    // Verificar si el servidor quiere salir
                    if (respuesta.equalsIgnoreCase("salir")) {
                        System.out.println("🚪 Terminando conversación...");
                        continuar = false;
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("❌ Error en servidor: " + e.getMessage());

        } finally {
            // 5. Cerrar recursos (ORDEN INVERSO a la creación)
            System.out.println("\n🔒 Cerrando conexiones...");
            cerrarRecursos(entrada, salida, teclado, socketCliente, servidor);
            System.out.println("✅ Servidor finalizado.");
        }
    }

    // Método auxiliar para cerrar todos los recursos de forma segura
    private static void cerrarRecursos(BufferedReader entrada,
                                       PrintWriter salida,
                                       Scanner teclado,
                                       Socket socketCliente,
                                       ServerSocket servidor) {
        try {
            if (entrada != null) entrada.close();
        } catch (IOException e) {
            System.err.println("Error cerrando entrada: " + e.getMessage());
        }

        if (salida != null) salida.close();

        if (teclado != null) teclado.close();

        try {
            if (socketCliente != null && !socketCliente.isClosed())
                socketCliente.close();
        } catch (IOException e) {
            System.err.println("Error cerrando socket cliente: " + e.getMessage());
        }

        try {
            if (servidor != null && !servidor.isClosed())
                servidor.close();
        } catch (IOException e) {
            System.err.println("Error cerrando servidor: " + e.getMessage());
        }
    }
}

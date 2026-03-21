package ejercicio02;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * 2) TCP Multi-mensaje: “Conversación hasta ‘salir’”
 * Objetivo: mantener conexión abierta y mandar varios mensajes hasta escribir salir.
 * Checklist
 *  Usar bucle en cliente y servidor
 *  Si mensaje == "salir" → cerrar conexión
 *  Cada mensaje del cliente debe generar respuesta del servidor
 *  Evitar bloqueos (orden correcto: enviar/recibir)
 */

public class Cliente {
    public static void main(String[] args) {
        final String SERVIDOR = "localhost";
        final int PUERTO = 5001;

        Socket socket = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;
        Scanner teclado = null;

        System.out.println("=== CLIENTE CHAT TCP ===");
        System.out.println("Escribe 'salir' para terminar la conversación\n");

        try {
            // 1. Conectar al servidor
            System.out.println("🔗 Conectando a " + SERVIDOR + ":" + PUERTO);
            socket = new Socket(SERVIDOR, PUERTO);
            System.out.println("✅ Conectado al servidor");
            System.out.println("\n--- INICIA CONVERSACIÓN ---");
            System.out.println("(Escribe tu mensaje y presiona Enter)");

            // 2. Configurar streams
            entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);
            teclado = new Scanner(System.in);

            // 3. Bucle principal de conversación
            boolean continuar = true;
            while (continuar) {
                // A. Leer mensaje del usuario (cliente)
                System.out.print("\nTú: ");
                String mensajeEnviar = teclado.nextLine();

                // B. Enviar mensaje al servidor
                salida.println(mensajeEnviar);

                // C. Verificar si es comando de salida
                if (mensajeEnviar.equalsIgnoreCase("salir")) {
                    System.out.println("🚪 Terminando conversación...");
                    continuar = false;
                }

                // Solo esperar respuesta si no hemos salido
                if (continuar) {
                    // D. Esperar respuesta del servidor
                    System.out.print("[Esperando servidor]... ");
                    String respuestaServidor = entrada.readLine();

                    if (respuestaServidor == null) {
                        System.out.println("⚠️  Servidor cerró la conexión.");
                        break;
                    }

                    System.out.println("Servidor: " + respuestaServidor);

                    // Verificar si el servidor quiere salir
                    if (respuestaServidor.equalsIgnoreCase("salir") ||
                            respuestaServidor.contains("Adiós")) {
                        System.out.println("🚪 Servidor terminó la conversación.");
                        continuar = false;
                    }
                }
            }

        } catch (ConnectException e) {
            System.err.println("❌ No se pudo conectar al servidor: " + e.getMessage());
            System.err.println("   Verifica que el servidor esté ejecutándose.");

        } catch (SocketException e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());

        } catch (IOException e) {
            System.err.println("❌ Error de E/S: " + e.getMessage());

        } finally {
            // 4. Cerrar recursos
            System.out.println("\n🔒 Cerrando conexión...");
            cerrarRecursos(entrada, salida, teclado, socket);
            System.out.println("✅ Cliente finalizado.");
        }
    }

    // Método auxiliar para cerrar recursos del cliente
    private static void cerrarRecursos(BufferedReader entrada,
                                       PrintWriter salida,
                                       Scanner teclado,
                                       Socket socket) {
        try {
            if (entrada != null) entrada.close();
        } catch (IOException e) {
            System.err.println("Error cerrando entrada: " + e.getMessage());
        }

        if (salida != null) salida.close();

        if (teclado != null) teclado.close();

        try {
            if (socket != null && !socket.isClosed())
                socket.close();
        } catch (IOException e) {
            System.err.println("Error cerrando socket: " + e.getMessage());
        }
    }
}

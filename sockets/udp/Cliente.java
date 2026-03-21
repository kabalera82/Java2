package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;

/**
 * Cliente UDP
 * 1. Prepara el mensaje.
 * 2. Lo empaqueta con la dirección del servidor.
 * 3. Lo envía.
 * 4. Se queda esperando una respuesta.
 */
public class Cliente {

    public static void main(String[] args) {

        // Configuración de conexión
        final int PUERTO_SERVIDOR = 5000;
        final String HOST = "127.0.0.1"; // Localhost

        System.out.println("🔵 INICIANDO CLIENTE UDP...");

        try {
            // 1. Obtener la dirección IP del servidor
            // ERROR ORIGINAL: getByAddress espera un array de bytes. Para un String usamos getByName.
            InetAddress direccionServidor = InetAddress.getByName(HOST);

            // 2. Crear el Socket del Cliente
            // No especificamos puerto en el constructor. El SO nos asignará uno libre aleatorio.
            DatagramSocket socket = new DatagramSocket();

            // ---------------- ENVIAR MENSAJE (REQUEST) ----------------
            String mensaje = "Hola desde el cliente UDP!";
            byte[] bufferEnvio = mensaje.getBytes();

            // 3. Crear el paquete para enviar
            // Necesitamos: (Datos, Longitud, IP Destino, Puerto Destino)
            DatagramPacket paqueteEnvio = new DatagramPacket(
                    bufferEnvio,
                    bufferEnvio.length,
                    direccionServidor,
                    PUERTO_SERVIDOR
            );

            // 4. Enviar el paquete
            System.out.println("📤 Enviando mensaje al servidor...");
            socket.send(paqueteEnvio);

            // ---------------- RECIBIR RESPUESTA (RESPONSE) ----------------
            // 5. Preparar buffer para la respuesta
            byte[] bufferRecepcion = new byte[1024];
            DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);

            // 6. Esperar respuesta (Bloqueante)
            System.out.println("⏳ Esperando respuesta del servidor...");
            socket.receive(paqueteRecepcion);

            // 7. Procesar y mostrar la respuesta
            String respuesta = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());
            System.out.println("📩 Respuesta del servidor: " + respuesta);

            // 8. Cerrar el socket
            socket.close();
            System.out.println("🔴 Cliente cerrado.");

        } catch (IOException e) {
            System.err.println("❌ Error en el cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
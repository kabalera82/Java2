package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Cliente TCP (El que llama)
 * Esta clase representa a un cliente que busca conectarse a un servidor,
 * enviarle un saludo y esperar su confirmación.
 * Pasos clave:
 * Busca al servidor por IP y Puerto.
 * Establece conexión (Handshake).
 * Envía datos -> Espera respuesta.
 * Cierra conexión.
 */
public class Cliente {

    public static void main(String[] args) {

        // 1. Configuración de destino
        final String HOST = "127.0.0.1"; // Localhost (mi propia máquina)
        final int PUERTO = 5000;         // Debe coincidir con el del servidor

        // Variables de flujo
        DataInputStream in;
        DataOutputStream out;

        try {
            // 2. Intentar conectar (Three-way handshake)
            // Si el servidor no está encendido en este punto, lanzará excepción 'ConnectionRefused'
            System.out.println("🔄 Intentando conectar a " + HOST + ":" + PUERTO + "...");
            Socket sc = new Socket(HOST, PUERTO);
            System.out.println("✅ ¡Conectado al servidor!");

            // 3. Inicializar flujos (Input/Output)
            in = new DataInputStream(sc.getInputStream());   // Canal de entrada
            out = new DataOutputStream(sc.getOutputStream()); // Canal de salida

            // 4. Enviar mensaje (Request)
            String mensaje = "Hola Mundo desde el cliente";
            out.writeUTF(mensaje);
            System.out.println("📤 He enviado: " + mensaje);

            // 5. Esperar respuesta (Response) - BLOQUEANTE
            // El programa se para aquí hasta que el servidor responda algo.
            String respuesta = in.readUTF();
            System.out.println("📥 El servidor me responde: " + respuesta);

            // 6. Cerrar conexión (Good Bye)
            // Es buena práctica cerrar siempre para liberar el puerto.
            sc.close();
            System.out.println("👋 Conexión cerrada.");

        } catch (IOException e) {
            // Captura errores de red (servidor apagado, cable desconectado, etc.)
            System.err.println("❌ Error de comunicación: " + e.getMessage());
        }
    }
}
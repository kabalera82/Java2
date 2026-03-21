package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor TCP (El que escucha)
 * Esta clase implementa un servidor que se mantiene a la espera de conexiones
 * entrantes en un puerto específico.
 * Flujo de ejecución:
 * Publica el puerto (abre el negocio).
 * Espera (bloqueado) a que llegue un cliente.
 * Cuando llega, acepta la conexión y crea los flujos de datos.
 * Lee lo que manda el cliente, responde y cierra esa atención.
 * Vuelve a esperar al siguiente (bucle infinito).
 */
public class Servidor {

    public static void main(String[] args) {

        // 1. Declaración de variables clave
        ServerSocket serverSocket = null; // El "portero" que gestiona el puerto
        Socket socketCliente = null;      // La "atención personalizada" para cada cliente

        // Flujos de comunicación (Tuberías)
        DataInputStream in;   // Para LEER (Oído)
        DataOutputStream out; // Para ESCRIBIR (Boca)

        // Puerto de comunicación (Debe ser > 1024 para no requerir permisos de admin)
        final int PUERTO = 5000;

        try {
            // 2. Iniciamos el servidor (Bind)
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("✅ SERVIDOR INICIADO: Escuchando en puerto " + PUERTO);

            // 3. Bucle infinito: El servidor nunca duerme (habitualmente)
            while(true){

                System.out.println("⏳ Esperando a un nuevo cliente...");

                // 4. Aceptar conexión (BLOQUEANTE)
                // ¡IMPORTANTE! La línea de abajo DETIENE el programa hasta que entra un cliente.
                socketCliente = serverSocket.accept();

                System.out.println("🤝 Cliente conectado: " + socketCliente.getInetAddress());

                // 5. Establecer las tuberías de comunicación
                // getInputStream: Lo que me llega DEL cliente
                in = new DataInputStream(socketCliente.getInputStream());
                // getOutputStream: Lo que yo le envío AL cliente
                out = new DataOutputStream(socketCliente.getOutputStream());

                // 6. Protocolo de comunicación (Leer petición)
                // readUTF() también es BLOQUEANTE. Espera hasta recibir datos.
                String mensajeRecibido = in.readUTF();
                System.out.println("📩 Mensaje recibido del cliente: " + mensajeRecibido);

                // 7. Responder al cliente
                out.writeUTF("¡Hola! Soy el servidor. He recibido tu mensaje correctamente.");

                // 8. Cerrar la conexión con ESTE cliente
                socketCliente.close();
                System.out.println("❌ Cliente desconectado. Fin de la atención.\n");
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
        // Nota: En un código real, serverSocket.close() iría aquí, pero como hay un while(true), nunca se alcanza.
    }
}
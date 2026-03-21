package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;

/**
 *Servidor UDP (El Buzón)
 * A diferencia de TCP, este servidor NO mantiene una conversación continua ("tubería")
 * con el cliente. Simplemente espera a que le llegue un paquete ("carta"),
 * lo lee, mira el remite y responde.
 */
public class Servidor {

    public static void main(String[] args) {
        // 1. Configuración del Servidor
        final int PUERTO = 5000;
        // El Buffer es el espacio reservado en memoria para recibir los datos.
        // 1024 bytes (1KB) suele ser suficiente para mensajes de texto simples.
        byte[] buffer = new byte[1024];
        System.out.println("🟢 INICIANDO SERVIDOR UDP...");
        try {
            // 2. Creación del Socket (El Buzón)
            // Al poner el PUERTO aquí, decimos: "Escucha todo lo que llegue al puerto 5000"
            DatagramSocket socket = new DatagramSocket(PUERTO);
            System.out.println("✅ Servidor escuchando en el puerto " + PUERTO);
            // 3. Bucle infinito (El servidor siempre está abierto)
            while (true) {
                System.out.println("⏳ Esperando datagrama (paquete)...");
                // ---------------- RECIBIR PETICIÓN (REQUEST) ----------------
                // 4. Preparamos el "envase" vacío para recibir el paquete
                // Le pasamos el buffer (donde se guardan los datos) y su tamaño máximo.
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                // 5. Recibir (BLOQUEANTE)
                // El servidor se duerme aquí hasta que alguien le lanza un paquete.
                // NO hay accept(), hay receive().
                socket.receive(peticion);
                // 6. Procesar el mensaje
                // Convertimos los bytes recibidos a String.
                // IMPORTANTE: Usamos peticion.getLength() para leer SOLO lo que nos han mandado,
                // no todo el buffer lleno de ceros.
                String mensaje = new String(peticion.getData(), 0, peticion.getLength());
                System.out.println("📩 Recibido: " + mensaje);
                // ---------------- ENVIAR RESPUESTA (RESPONSE) ----------------
                // 7. Obtener la dirección del remitente
                // En TCP la conexión ya sabe a quién responder. En UDP NO.
                // Tenemos que mirar el "remite" del paquete que nos acaba de llegar.
                InetAddress direccionCliente = peticion.getAddress();
                int puertoCliente = peticion.getPort();

                // 8. Preparar la respuesta
                String respuestaTexto = "¡Hola desde el Servidor UDP!";
                byte[] respuestaBytes = respuestaTexto.getBytes();

                // 9. Crear el paquete de respuesta
                // Necesitamos: (Datos, Longitud, IP Destino, Puerto Destino)
                DatagramPacket respuesta = new DatagramPacket(
                        respuestaBytes,
                        respuestaBytes.length,
                        direccionCliente,
                        puertoCliente
                );
                // 10. Enviar el paquete
                socket.send(respuesta);
                System.out.println("📤 Respuesta enviada al cliente (" + direccionCliente + ":" + puertoCliente + ")\n");

                // Nota: No cerramos el socket dentro del while, o dejaríamos de escuchar.
            }

        } catch (IOException e) {
            System.err.println("❌ Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
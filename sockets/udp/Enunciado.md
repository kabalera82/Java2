package udp;

import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.IOException;

/**
* <h1 style="color:orange">Cliente UDP</h1>
* <p>
* A diferencia de TCP, aquí NO creamos una conexión permanente.
* Empaquetamos los datos en un "sobre" (Datagrama) y lo lanzamos.
*/
public class Cliente {

    public static void FizzBuzz(String[] args) {

        // 1. Configuración del Destino (A quién enviamos la carta)
        final int PUERTO_SERVIDOR = 5000;
        final String HOST = "127.0.0.1";
        // Buffer = El espacio de memoria para los datos (UDP trabaja con bytes, no con Strings directos)
        byte[] buffer = new byte[1024];
        try {
            // 2. Obtener la dirección IP del servidor
            // En UDP necesitamos el objeto InetAddress, no vale solo el String.
            InetAddress destino = InetAddress.getByName(HOST);
            // 3. Creación del Socket (El buzón de salida)
            // Como somos CLiente, no ponemos puerto en el constructor (el S.O. nos asigna uno libre).
            System.out.println("🚀 Iniciando el socket UDP...");
            DatagramSocket socketUDP = new DatagramSocket();

            // ---------------- ENVIAR DATOS (REQUEST) ----------------

            String mensaje = "Hola desde el Cliente UDP!";
            // ¡IMPORTANTE! Hay que convertir el String a bytes

            buffer = mensaje.getBytes();

            /*
            Creamos el paquete del datagrama
            - los datos (buffer)
            - el tamaño de los datos
            - la direccion del servidor
            - el puerto del servidor al que nos vamos a conectar
             */
            DatagramPacket paqueteEnvio = new DatagramPacket(
                    buffer,
                    buffer.length,
                    destino,
                    PUERTO_SERVIDOR
            );

            System.out.println("Envio del datagrama al servidor");
            // 4. Preparamos el Paquete (La carta)
            // Constructor para ENVIAR: (Datos, Longitud, IP Destino, Puerto Destino)

            // 5. Enviar el paquete (Lanzar y olvidar)
            socketUDP.send(paqueteEnvio);
            System.out.println("📤 Paquete enviado a " + HOST + ":" + PUERTO_SERVIDOR);

            // ---------------- RECIBIR RESPUESTA (RESPONSE) ----------------

            // 6. Preparamos un paquete VACÍO para recibir la respuesta
            // Constructor para RECIBIR: (Buffer donde guardar, Longitud máxima)
            DatagramPacket paqueteRecepcion = new DatagramPacket(buffer, buffer.length);

            System.out.println("⏳ Esperando respuesta...");
            // 7. Recibir (Bloqueante)
            // El programa se para aquí hasta que llegue un paquete al buzón.
            socketUDP.receive(paqueteRecepcion);

            // 8. Procesar lo recibido
            // Convertimos bytes a String. ¡Ojo! Solo convertimos lo que hemos recibido (getLength), no todo el buffer.
            String respuesta = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());
            System.out.println("📥 El servidor responde: " + respuesta);

            // 9. Cerrar el socket
            socketUDP.close();
            System.out.println("👋 Cliente cerrado.");

        } catch (SocketException e) {
            System.err.println("❌ Error de Socket: " + e.getMessage());
        } catch (UnknownHostException e) {
            System.err.println("❌ No se encuentra el host: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("❌ Error de Entrada/Salida: " + e.getMessage());
        }
    }
}
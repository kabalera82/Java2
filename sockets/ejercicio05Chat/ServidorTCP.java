package ejercicio05Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * login + menu
 * Objetivo;: el serviro tiene un mini sistema:
 * 1 pide usuario y contraseña
 * si muestra ok un menu
 * 1- Hora
 * 2- Random
 * 3- Salir
 *
 * Checklist:
 *  - cliente envia usuario/contraseña
 *  - Servidor valida(con HashMap)
 *  - Si fallo: devuelve "DENEGADO" y corta
 *  - Si ok: mantener conecion ocn menu
 *  - Opciuon hora devuelve fecha /hora
 *  - Opcion random devuelve numero aleatorio
 */
public class ServidorTCP {

    public static void main (String[] args){

        final int PUERTO = 5000;


        Map<String, String> usuarios = new HashMap<>();
        usuarios.put("Raul", "1234");
        usuarios.put("alumno", "dam");
        usuarios.put("admin", "admin");

        Random r = new Random();

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor Login+Menú iniciado en el puerto " + PUERTO);

            while (true) {
                Socket sc = servidor.accept();
                System.out.println("Cliente conectado: " + sc.getInetAddress() + ":" + sc.getPort());
                try (
                        sc;
                        DataInputStream in = new DataInputStream(sc.getInputStream());
                        DataOutputStream out = new DataOutputStream(sc.getOutputStream())
                ) {
                    out.writeUTF("LOGIN: Introduce usuario");
                    String user = in.readUTF();

                    out.writeUTF("LOGIN: Introduce una contraseña");
                    String pass = in.readUTF();

                    boolean loginOk = usuarios.containsKey(user) && usuarios.get(user).equals(pass);

                    if (!loginOk) {
                        out.writeUTF("DENEGADO");
                        System.out.println("Login fallido para usuario: " + user);
                    } else {
                        out.writeUTF("Ok");
                        System.out.println("Login correcto para usuario: " + user);

                        boolean enSesion = true;
                        while (enSesion) {

                            out.writeUTF(
                                    "MENÚ:\n" +
                                            "1) Hora\n" +
                                            "2) Random\n" +
                                            "3) Salir\n" +
                                            "Elige opción (1-3)"
                            );

                            String opcion = in.readUTF();

                            if (opcion.equals("1")) {
                                LocalDateTime ahora = LocalDateTime.now();
                                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                out.writeUTF("HORA SERVIDOR: " + ahora.format(fmt));
                            } else if (opcion.equals("2")) {
                                int num = r.nextInt(100) + 1;
                                out.writeUTF("RANDOM (1-100): " + num);
                            } else if (opcion.equals("3")) {
                                out.writeUTF("Sesión cerrada. ¡Hasta la próxima!");
                                enSesion = false;
                            } else {
                                out.writeUTF("Opción invalida. Debe ser 1, 2 o 3");
                            }


                        }

                    }

                }
                System.out.println("Fin de atención cliente");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
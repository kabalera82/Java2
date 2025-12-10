package Tema19ContructorProcesos.src.main.java;

import java.io.*;

/**
 * Ejercicio03
 * --------------------------------------------------------
 * Crea un proceso interactivo con el intérprete de comandos (cmd.exe),
 * le envía varios comandos y muestra su salida en consola.
 *
 * Requisitos:
 *  - No cerrar el intérprete automáticamente (sin usar /c)
 *  - Enviar varios comandos al proceso:
 *        echo ===== INICIO =====
 *        cd ..
 *        dir
 *        echo ===== FIN =====
 *        exit
 *  - Leer toda la salida del proceso y mostrarla en pantalla.
 *  - Esperar a que finalice y mostrar su código de salida.
 */
public class Ejercicio03 {
    public static void main(String[] args) {
        try {
            // =====================================================
            // 🔹 CREACIÓN DEL PROCESO CMD
            // =====================================================
            // No usamos /c para mantenerlo abierto hasta que enviemos "exit"
            ProcessBuilder pb = new ProcessBuilder("cmd");

            // Iniciar el proceso
            Process proceso = pb.start();

            // =====================================================
            // 🔹 CONFIGURAR LOS FLUJOS DE ENTRADA/SALIDA
            // =====================================================
            // Flujo para enviar comandos al proceso
            BufferedWriter entrada = new BufferedWriter(
                    new OutputStreamWriter(proceso.getOutputStream())
            );

            // Flujo para leer la salida generada por el proceso
            BufferedReader salida = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream())
            );

            // =====================================================
            // 🔹 ENVIAR COMANDOS AL PROCESO
            // =====================================================
            entrada.write("echo ===== INICIO =====\n");
            entrada.write("cd ..\n");
            entrada.write("dir\n");
            entrada.write("echo ===== FIN =====\n");
            entrada.write("exit\n");  // 🔸 Cerrar el intérprete
            entrada.flush();

            // =====================================================
            // 🔹 LEER Y MOSTRAR LA SALIDA DEL PROCESO
            // =====================================================
            String linea;
            while ((linea = salida.readLine()) != null) {
                System.out.println(linea);
            }

            // =====================================================
            // 🔹 ESPERAR A QUE TERMINE Y MOSTRAR EL CÓDIGO DE SALIDA
            // =====================================================
            int exitCode = proceso.waitFor();
            System.out.println("\n✅ Proceso finalizado con código: " + exitCode);

            // =====================================================
            // 🔹 CERRAR RECURSOS
            // =====================================================
            entrada.close();
            salida.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

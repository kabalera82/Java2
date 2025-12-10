package Tema19ContructorProcesos.src.main.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Ejercicio06
 * ----------------------------------------------
 * Ejecuta un comando del sistema operativo (dir en Windows)
 * usando ProcessBuilder y muestra la salida línea a línea.
 */
public class Ejercicio06 {
    public static void main(String[] args) {

        try {
            /* =====================================================
               🔹 CREACIÓN DEL PROCESO  En Windows se usa "cmd /c <comando>"
               En Linux/Mac sería:
               ProcessBuilder pb = new ProcessBuilder("ls", "-l");
             */
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir");

            // =====================================================
            // 🔹 INICIO DEL PROCESO
            Process process = pb.start();

            // =====================================================
            // 🔹 LECTURA DE LA SALIDA DEL PROCESO

            // getInputStream() devuelve lo que el proceso escribiría en consola
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String linea;
            // 🔸 CORRECCIÓN: el paréntesis de cierre iba mal colocado
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

            // =====================================================
            // 🔹 ESPERAR A QUE TERMINE EL PROCESO
            // =====================================================
            int exitCode = process.waitFor();
            System.out.println("\n✅ El proceso terminó con código: " + exitCode);

            // Cerrar recursos
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package Tema19ContructorProcesos.src.main.java;

import java.io.*;

public class Ejercicio07 {

    public static void main(String[] args) {

        try {
            // Crea un proceso que ejecuta "cmd /c dir"
            // /c ejecuta el comando y cierra el intérprete
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir");
            Process p = pb.start();

            // Flujo para enviar datos al proceso (no funcionará porque /c cierra el cmd)
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            writer.write("echo Hola Mundo"); // no se ejecutará
            writer.flush();     // flush() fuerza el envío inmediato al proceso (sin esperar más texto)
            writer.write("exit\n");          // tampoco se ejecutará
            writer.flush();

            // Flujo para leer la salida del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea); // imprime la salida del comando "dir"
            }

            p.waitFor();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

package ejercicio07GestorProcesos;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class GestorProcesos {

    // Ejecuta un comando y muestra su salida normal en consola
    public void ejecutarComando(String comando) {
        try {
            // cmd /c COMANDO → ejecuta el comando en Windows y termina
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
            Process p = pb.start();

            // Lee la salida estándar del proceso
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream())
            );

            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);  // Muestra la salida del comando
            }

            p.waitFor(); // Espera a que finalice el proceso

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Redirige la salida normal del comando a un archivo
    public void redirigirSalida(String comando, String archivo) {

        try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);

            // Envía la salida directamente a un archivo
            pb.redirectOutput(new File(archivo));

            Process p = pb.start();
            p.waitFor();

            System.out.println("Salida guardada en " + archivo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Muestra únicamente los errores que genere el comando
    public void mostrarErrores(String comando) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
            Process p = pb.start();

            // getErrorStream() → solo la salida de error (STDERR)
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getErrorStream())
            );

            String linea;
            while ((linea = br.readLine()) != null) {
                System.err.println(linea);  // Muestra los errores del comando
            }

            p.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


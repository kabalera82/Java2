import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Ej01MostrarDirectorioActual {

    /*
    Crea un programa que ejecute el comando del sistema operativo que muestra el contenido
    del directorio actual (dir en Windows o ls en Linux) y muestre la salida por pantalla.
    */

    public static void main(String[] args) {
        try {

            // Abrimos el proveso cmd
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir");
            Process proceso = pb.start();

            // Preparamos un escritor para enviar comandos al CMD
            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

            // Mostramos solo la primera línea
            String primeraLinea = br.readLine();
            if (primeraLinea != null) {
                System.out.println("Primera línea de salida:");
                System.out.println(primeraLinea);
            } else {
                System.out.println("No se ha producido ninguna salida.");
            }

            proceso.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

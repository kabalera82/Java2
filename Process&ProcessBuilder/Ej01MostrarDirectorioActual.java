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
            BufferedWriter escritor = new BufferedWriter(
                    new OutputStreamWriter(proceso.getOutputStream())
            );

            // Enviamos el comando al CMD
            escritor.write("Vamos a ejecutar en cmd el dir\n");
            escritor.flush();

            // Lector para recibir la salida

            BufferedReader lector = new BufferedReader(
              new InputStreamReader(proceso.getInputStream())
            );

            // Muestra en pantalla lo que devuelve el CMD
            String linea;
            while((linea = lector.readLine()) != null){
                System.out.println(linea);
            }

        } catch (Exception e) {
            // Muestra información del error si algo falla
            e.printStackTrace();
        }
    }
}

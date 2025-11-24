import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ej14EjecutarComando {

    /*
     * Ejercicio 14 – Clase utilitaria
     * Crea un método estático ejecutar(String comando)
     * que devuelva la salida del comando como un String.
     */

    public static void main(String[] args) {
        String salida = ejecutar("dir");  // ejemplo
        System.out.println(salida);
    }

    public static String ejecutar(String comando) {
        StringBuilder salida = new StringBuilder();

        try {
            // Ejecuta el comando en Windows
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
            pb.redirectErrorStream(true);

            Process proceso = pb.start();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()))) {

                String linea;
                while ((linea = reader.readLine()) != null) {
                    salida.append(linea).append(System.lineSeparator());
                }
            }

            proceso.waitFor();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al ejecutar el comando: " + comando, e);
        }

        return salida.toString();
    }
}

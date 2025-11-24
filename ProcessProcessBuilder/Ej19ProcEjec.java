/*
Ejercicio 19 – Mostrar procesos en ejecución
Ejecuta el comando tasklist (Windows) o ps -e (Linux) y guarda la salida en procesos.txt.
*/

import java.io.File;
import java.io.IOException;

public class Ej19ProcEjec {

    public static void main(String[] args) {
        boolean esWindows = System.getProperty("os.name").toLowerCase().contains("win");

        String comando = esWindows ? "tasklist" : "ps -e";

        try {
            ProcessBuilder pb;
            if (esWindows) {
                pb = new ProcessBuilder("cmd", "/c", comando);
            } else {
                pb = new ProcessBuilder("bash", "-c", comando);
            }

            pb.redirectErrorStream(true);

            // Redirigir la salida a un fichero
            File fichero = new File("procesos.txt");
            pb.redirectOutput(fichero);

            Process proceso = pb.start();
            int exitCode = proceso.waitFor();

            System.out.println("Salida de procesos guardada en: " + fichero.getAbsolutePath());
            System.out.println("Código de salida: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

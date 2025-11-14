package ejercicio05InheritIO;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        // Este programa ejecuta el comando "dir" dentro del directorio C:\Windows.
        // Al usar pb.inheritIO(), la salida se envía directamente a la consola real,
        // sin que Java la capture ni la procese. Java solo lanza el comando.

        try {
            // Ejecuta cmd /c dir
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir");

            // Define el directorio donde se ejecutará el comando
            pb.directory(new File("C:\\Windows"));

            // Usa la misma consola que el programa Java (STDIN/STDOUT heredados)
            pb.inheritIO();

            // Inicia el proceso
            pb.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

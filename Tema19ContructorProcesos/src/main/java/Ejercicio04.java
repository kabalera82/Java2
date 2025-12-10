package Tema19ContructorProcesos.src.main.java;

import java.io.File;
import java.io.IOException;

public class Ejercicio04 {

    public static void main(String[] args) {
        try {
            // Crea el proceso que ejecuta "cmd /c dir"
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir");

            // Cambia el directorio de trabajo del proceso
            pb.directory(new File("C:\\Windows"));

            // Muestra la salida directamente en la consola
            pb.inheritIO();

            // Inicia el proceso
            Process proceso = pb.start();

            // Espera a que termine
            int exitCode = proceso.waitFor();
            System.out.println("Proceso finalizado con código: " + exitCode);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

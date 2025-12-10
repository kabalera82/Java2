package Tema19ContructorProcesos.src.main.java;

import java.io.File;

public class ejercicio08 {

    public static void main(String[] args) {
        try {
            // Ejecuta el comando "dir" dentro de la carpeta C:\Windows
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir");

            // Cambia el directorio de trabajo
            // pasamos por parametro un fichero con ruta absoluta
            pb.directory(new File("C:\\Windows"));

            // HAce que el proceso utilice la misma consola java
            pb.inheritIO();

            // Inicia el proceso y espera a que termine
            Process p = pb.start();
            p.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

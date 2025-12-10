package Tema19ContructorProcesos.src.main.java;

import java.io.File;

public class Ejercicio02 {
    public static void main(String[] args) {

        /**
         * redirigir salida a un archivo
         */
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "cmd",    // Ejecuta el intérprete de comandos de Windows (cmd.exe)
                    "/c",               // /c → ejecuta el comando y cierra
                    "dir"               // Comando que se ejecutará: lista los archivos y carpetas del directorio actual
            );

            pb.redirectOutput(new File("data/resultado.txt"));
            pb.redirectError(new File ("errores.txt"));
            pb.start();
            System.out.println("Comando ejecutado. Revisa resultados en el .txt");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

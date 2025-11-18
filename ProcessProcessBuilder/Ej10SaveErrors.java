/*
 Ejecuta un comando que genere salida y errores (por ejemplo, dir /xyz)
 y redirige la salida a salida.txt y los errores a errores.txt.
*/

import java.io.File;

public class Ej10SaveErrors {


    public static void main(String[] args) {
        ejecutarComando();
    }

    public static void ejecutarComando() {

        try {


            // Creamos el ProcessBuilder
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir /xyz");

            // Archivos destino
            pb.redirectOutput(new File("salida.txt"));   // salida estándar
            pb.redirectError(new File("errores.txt"));   // errores

            // Ejecutar el comando
            Process proceso = pb.start();

            // Esperar a que termine
            proceso.waitFor();

            System.out.println("Archivos generados: salida.txt y errores.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


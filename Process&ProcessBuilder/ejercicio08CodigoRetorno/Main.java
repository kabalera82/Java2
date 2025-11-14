package ejercicio08CodigoRetorno;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {


        // Este programa ejecuta ipconfig y combina la salida normal y de error
        // con redirectErrorStream(true). Lee toda la salida con un solo lector
        // y muestra cada línea. Después obtiene el código de retorno del proceso
        // y muestra un mensaje indicando si la ejecución fue correcta o hubo error.


        try {
            // Ejecuta el comando "ipconfig" en Windows
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "ipconfig");

            // Une la salida normal (STDOUT) y la de errores (STDERR) en un solo stream
            pb.redirectErrorStream(true);

            // Inicia el proceso
            Process p = pb.start();

            // Lector para capturar toda la salida generada por el comando
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream())
            );

            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea); // Muestra cada línea que devuelva ipconfig
            }

            // Código de salida del proceso (0 = correcto)
            int codigo = p.waitFor();
            System.out.println("Script finalizado con código " + codigo);

            // Mensaje según el resultado del proceso
            if (codigo == 0) {
                System.out.println("Copia completada correctamente");
            } else {
                System.out.println("Error en la copia");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

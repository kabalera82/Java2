package Tema19ContructorProcesos.src.main.java.ejercicio11;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        try {
            // Crea un ProcessBuilder para ejecutar el archivo batch "backup.bat" con cmd
            //ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "backup.bat");
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "ipconfig");
            // Redirige la salida de error al mismo flujo que la salida estándar
            pb.redirectErrorStream(true);

            // Inicia el proceso (ejecuta el script)
            Process p = pb.start();

            // Prepara un lector para capturar la salida que genera el proceso (línea a línea)
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String linea;
            // Mientras haya líneas de salida, las imprime en la consola
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            // Espera a que el proceso (el script) termine y guarda su código de salida
            int codigo = p.waitFor();

            // Muestra el código devuelto por el proceso
            System.out.println("Script finalizado con código: " + codigo);

            // Si el código es 0, se considera que terminó correctamente
            if (codigo == 0) {
                System.out.println("Copia completada correctamente");
            }
            // Cualquier otro código indica un error
            else {
                System.out.println("Error en la copia.");
            }

        } catch (Exception e) {
            // Si algo falla (error de ejecución o de E/S), muestra la traza del error
            e.printStackTrace();
        }
    }
}

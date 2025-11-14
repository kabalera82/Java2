package ejercicio02DirCommand;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        // Este programa ejecuta el comando "dir" usando ProcessBuilder
        // pero NO muestra la salida directamente en la consola del sistema.
        // En su lugar, Java lee la salida del proceso usando un BufferedReader,
        // línea por línea, y luego la imprime con System.out.println().
        // Es útil cuando queremos procesar, analizar o guardar la salida del comando.

        try {
            // Crea un proceso del sistema operativo:
            // "cmd /c dir" → ejecuta el comando DIR en Windows
            ProcessBuilder pb = new ProcessBuilder("cmd","/c","dir");

            // Arranca el proceso
            Process proceso = pb.start();

            // Prepara un lector para recoger la salida del proceso (lo que imprime DIR)
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream())
            );

            // Lee línea a línea la salida del proceso y la muestra por consola
            String linea;
            while((linea = reader.readLine()) != null){
                System.out.println(linea);
            }

            // Espera a que el proceso termine y captura el código de salida
            int exitCode = proceso.waitFor();
            System.out.println("El proceso terminó con código "+exitCode);

        } catch (Exception e) {
            // Muestra información del error si algo falla
            e.printStackTrace();
        }
    }
}

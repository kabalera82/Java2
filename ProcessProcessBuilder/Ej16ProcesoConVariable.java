/*
Ejercicio 16 – Ejecutar un proceso con variable de entorno personalizada
Haz que el programa establezca una variable de entorno (JAVA_HOME o MI_NOMBRE) y
luego ejecute un comando que la muestre por pantalla.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Ej16ProcesoConVariable {
    public static void main(String[] args) {
        String variable = "MI_NOMBRE";
        String valor = "Marcos";

        boolean esWindows = System.getProperty("os.name").toLowerCase().contains("win");
        String comandoMostrarVar = esWindows ? "echo %" + variable + "%" : "echo $" + variable;

        try {
            ProcessBuilder pb;
            if (esWindows) {
                pb = new ProcessBuilder("cmd", "/c", comandoMostrarVar);
            } else {
                pb = new ProcessBuilder("bash", "-c", comandoMostrarVar);
            }

            // Añadimos la variable de entorno personalizada
            Map<String, String> entorno = pb.environment();
            entorno.put(variable, valor);

            pb.redirectErrorStream(true);
            Process proceso = pb.start();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()))) {

                String linea;
                System.out.println("Mostrando la variable de entorno " + variable + ":");
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }
            }

            int exitCode = proceso.waitFor();
            System.out.println("Código de salida del proceso: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

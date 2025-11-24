/*
Ejercicio 17 – Repetir ejecución
Crea un programa que ejecute un mismo comando 3 veces seguidas (por ejemplo, echo
Hola mundo) y muestre el número de ejecución.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ej17RepetirEjecucion {

    public static void main(String[] args) {
        boolean esWindows = System.getProperty("os.name").toLowerCase().contains("win");
        String comando = esWindows ? "echo Hola mundo" : "echo 'Hola mundo'";

        for (int i = 1; i <= 3; i++) {
            System.out.println("=== Ejecución " + i + " ===");
            String salida = ejecutarComando(comando, esWindows);
            System.out.println(salida);
        }
    }

    private static String ejecutarComando(String comando, boolean esWindows) {
        StringBuilder salida = new StringBuilder();
        try {
            ProcessBuilder pb;
            if (esWindows) {
                pb = new ProcessBuilder("cmd", "/c", comando);
            } else {
                pb = new ProcessBuilder("bash", "-c", comando);
            }

            pb.redirectErrorStream(true);
            Process proceso = pb.start();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()))) {

                String linea;
                while ((linea = reader.readLine()) != null) {
                    salida.append(linea).append(System.lineSeparator());
                }
            }

            proceso.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return salida.toString();
    }
}

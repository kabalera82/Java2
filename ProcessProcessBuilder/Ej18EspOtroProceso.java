/*
Ejercicio 18 – Esperar tiempo antes de lanzar otro proceso
Ejecuta un proceso, espera 3 segundos (Thread.sleep(3000)), y luego lanza otro.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ej18EspOtroProceso {

    public static void main(String[] args) {
        boolean esWindows = System.getProperty("os.name").toLowerCase().contains("win");

        String comando1 = esWindows ? "echo Primer proceso" : "echo 'Primer proceso'";
        String comando2 = esWindows ? "echo Segundo proceso" : "echo 'Segundo proceso'";

        System.out.println("Lanzando primer proceso...");
        ejecutarComando(comando1, esWindows);

        System.out.println("Esperando 3 segundos...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Lanzando segundo proceso...");
        ejecutarComando(comando2, esWindows);
    }

    private static void ejecutarComando(String comando, boolean esWindows) {
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
                    System.out.println(linea);
                }
            }

            proceso.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

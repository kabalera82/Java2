/*
Haz que el programa detecte automáticamente si está en Windows o Linux, y ejecute un
comando diferente en cada caso (por ejemplo, ipconfig o ifconfig).
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ej12ComandoSegunSO {

    public static void main(String[] args) {

        System.out.println("SO");
        detectaSO();
    }

    public static void detectaSO() {

        try {
            String so = System.getProperty("os.name").toLowerCase();
            String comando = so.contains("win") ? "ipconfig" : "ifconfig";


            //Construimos el proceso
            ProcessBuilder proceso = so.contains("win")
                    ? new ProcessBuilder("cmd", "/c", comando)
                    : new ProcessBuilder("bash", "-c", comando);

            Process p = proceso.start();

            // Leer la salida del proceso
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

            // Mostramos solo la primera línea
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


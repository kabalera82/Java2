package ejercicio06RedirigirSalida;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        // Este programa ejecuta ipconfig (o ifconfig en Linux/Mac) y redirige toda
        // la salida del comando a un archivo llamado red.txt usando redirectOutput().
        // Java no imprime nada del proceso; simplemente guarda el resultado en el archivo.


        /*
         * Ejecuta "ipconfig" en Windows o "ifconfig" en Linux/Mac
         * y redirige toda la salida a un archivo llamado red.txt
         */

        try {
            // Detecta el SO para elegir el comando correcto
            String comando = System.getProperty("os.name").toLowerCase().contains("win")
                    ? "ipconfig"
                    : "ifconfig";

            // Crea el proceso para ejecutar el comando
            ProcessBuilder pb = new ProcessBuilder(comando);

            // Redirige la salida estándar del proceso a un archivo
            pb.redirectOutput(new File("red.txt"));

            // Inicia el proceso
            Process p = pb.start();

            // Espera a que termine la ejecución del comando
            p.waitFor();

            System.out.println("Salida guardada en red.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

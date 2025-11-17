package ejercicio03CMD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) {

        try {
            // Abre un proceso "cmd" (la consola de Windows)
            ProcessBuilder pb = new ProcessBuilder("cmd");
            Process p = pb.start();

            // Prepara un escritor para enviar comandos al CMD
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(p.getOutputStream())
            );

            // Envía un comando al CMD: imprime un mensaje
            writer.write("echo Hola 2º de DAM\n");
            writer.flush(); // obliga a enviar el texto al proceso

            // Envía comando EXIT para cerrar la sesión del CMD
            writer.write("exit\n");
            writer.flush();

            // Lector para recibir la salida que produzca el proceso
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(p.getInputStream())
            );

            // Muestra en pantalla lo que devuelve el CMD
            String linea;
            while((linea = reader.readLine()) != null){
                System.out.println(linea);
            }

            // Espera a que termine completamente el proceso
            p.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

package Tema19ContructorProcesos.src.main.java.ejercicio10;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class GestorProcesos {

    public static void main(String[] args) {

        // No se pueden declarar métodos dentro de main, así que se mueven fuera.
        // Aquí solo podrías llamar a ellos, no definirlos.

    }

    // Ejecuta un comando y muestra la salida en consola
    public static void ejecutarComando(String comando) {
        try {
            // Crea el proceso
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
            Process p = pb.start();

            // Lee la salida
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            p.waitFor(); // espera a que termine

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Ejecuta un comando y guarda su salida en un archivo
    public static void redirigirSalida(String comando, String archivo) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
            pb.redirectOutput(new File(archivo)); // redirige salida estándar

            Process p = pb.start();
            p.waitFor();

            System.out.println("Salida guardada en archivo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Muestra los errores del comando
    public static void mostrarErrores(String comando) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", comando);
            Process p = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
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

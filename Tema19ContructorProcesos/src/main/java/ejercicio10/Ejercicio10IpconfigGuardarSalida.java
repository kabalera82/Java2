package Tema19ContructorProcesos.src.main.java.ejercicio10;

import java.io.File;


public class Ejercicio10IpconfigGuardarSalida {

    public static void main(String[] args) {

        String comando = System.getProperty("os.name").toLowerCase().contains("win") ? "ipconfig" : "ifconfig";

        System.out.println(comando);

        try {
            // Ejecuta ipconfig desde CMD
            ProcessBuilder pb = new ProcessBuilder(comando);

            pb.redirectOutput(new File("red.text"));

            Process p = pb.start();

            p.waitFor();

            System.out.println("Salida guardada en red.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

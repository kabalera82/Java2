package Tema19ContructorProcesos.src.main.java;

import java.io.File;
import java.io.IOException;

public class Ejercicio05 {
    public static void main(String[] args) {
        try {
            // Ejecuta ipconfig desde CMD
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "ipconfig");

            // (opcional) abre el proceso heredando la consola actual
            pb.inheritIO();

            pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

/*
Ejercicio 20 – Menú interactivo
Haz un programa que muestre un pequeño menú con opciones:
1. Ver IP del equipo
2. Ver variables de entorno
3. Probar conexión con Google
El programa ejecutará los comandos correspondientes según la opción.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ej20MenuInteractivo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean esWindows = System.getProperty("os.name").toLowerCase().contains("win");
        int opcion;

        do {
            System.out.println("===== MENÚ =====");
            System.out.println("1. Ver IP del equipo");
            System.out.println("2. Ver variables de entorno");
            System.out.println("3. Probar conexión con Google");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            while (!sc.hasNextInt()) {
                System.out.print("Introduce un número válido: ");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> verIP(esWindows);
                case 2 -> verVariablesEntorno(esWindows);
                case 3 -> probarConexionGoogle(esWindows);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }

            System.out.println();

        } while (opcion != 0);

        sc.close();
    }

    private static void verIP(boolean esWindows) {
        String comando = esWindows ? "ipconfig" : "ip a";
        System.out.println("Mostrando información de IP:");
        System.out.println(ejecutarComando(comando, esWindows));
    }

    private static void verVariablesEntorno(boolean esWindows) {
        String comando = esWindows ? "set" : "printenv";
        System.out.println("Mostrando variables de entorno:");
        System.out.println(ejecutarComando(comando, esWindows));
    }

    private static void probarConexionGoogle(boolean esWindows) {
        String comando = esWindows ? "ping -n 4 www.google.com"
                : "ping -c 4 www.google.com";
        System.out.println("Probando conexión con Google:");
        System.out.println(ejecutarComando(comando, esWindows));
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

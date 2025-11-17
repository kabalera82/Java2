import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

    /*
    Haz que el programa pida al usuario un comando del sistema, lo ejecute y guarde tanto la
    salida como los errores en el archivo resultado.txt.
    */

public class Ej04InsertCommand {

    // RUTA Absoluta
    private static final Path RUTA = Path.of(
            "F:/Marcos/Programacion/Java/Java2/ProcessProcessBuilder/data/errores.txt"
    );
    // RUTA Absoluta
    private static final Path RUTA2 = Path.of(
            "F:/Marcos/Programacion/Java/Java2/ProcessProcessBuilder/data/comandos.txt"
    );

    public static void main(String[] args) {

        System.out.println("Inserte un comando:");
        Scanner sc = new Scanner(System.in);
        String comando = sc.nextLine();
        insertCommand(comando);
    }


    private static void insertCommand (String comando) {
        try{

            // Comando inexistente → generará error
            ProcessBuilder pr = new ProcessBuilder("powershell", "-command", "asdf");

            // Esto mantiene salida (STDOUT) y error (STDERR) separados
            pr.redirectErrorStream(false); // <- en false

            // iniciamos el proceso
            Process p = pr.start();

            // Capturamos la salida
            BufferedReader salida = new BufferedReader(new InputStreamReader(p.getInputStream()));

            // Esto captura el error
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // Crea carpeta si no existe
            Files.createDirectories(RUTA.getParent());

            // Abrir archivo de errores en modo append
            try (BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(RUTA.toFile(), true),
                            StandardCharsets.UTF_8
                    ))) {

                String linea;

                // ===== Leer SOLO ERRORES =====
                while ((linea = error.readLine()) != null) {
                    if (linea.isBlank()) continue;
                    bw.write(linea);
                    bw.newLine();
                }
            }

            // Esperar a que termine
            p.waitFor();

            // (Opcional) Mostrar salida normal en consola
            String out;
            while ((out = salida.readLine()) != null) {
                System.out.println("[SALIDA] " + out);
            }

            System.out.println("Errores guardados correctamente en " + RUTA.toAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }




import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ej03LogErrores {

    /*
    Crea un programa que intente ejecutar un comando inexistente y redirija los mensajes de
    error a un archivo llamado errores.txt.
    */


    // RUTA Absoluta
    private static final Path RUTA = Path.of(
            "F:/Marcos/Programacion/Java/Java2/Process&ProcessBuilder/data/errores.txt"
    );


    public static void main(String[] args) {


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

            System.out.println("Errores guardados correctamente en " + RUTA.toAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

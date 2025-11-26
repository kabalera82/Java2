


    /*
    Ejercicio 15 – Crear copia de seguridad
    Crea un script .bat o .sh que copie una carpeta a otra y un programa Java que lo ejecute y
    diga si ha sido exitoso.
    */

    import java.io.*;
    import java.nio.charset.StandardCharsets;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;

    public class Ej15CopiaSeg {
        public static final Path RUTA = Paths.get("data/CopiaSeguridad.txt");

    public static void main(String[] args) {

    ejecutar();
    }

    public static void ejecutar(){
        try {
            // Ejecuta PowerShell para obtener fecha + hora formateada
            ProcessBuilder proceso = new ProcessBuilder(
                    "powershell", "-command", "Get-Date"
            );

            // Une la salida estándar y la salida de error en un solo stream
            proceso.redirectErrorStream(true);

            // Inicia el proceso externo
            Process p = proceso.start();

            // Lector para obtener línea a línea la salida del comando
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream())
            );

            // Asegura que la carpeta existe (si no, la crea)
            Files.createDirectories(RUTA.getParent());

            // Abre el archivo en modo APPEND: añade sin sobrescribir
            try (BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(RUTA.toFile(), true),
                            StandardCharsets.UTF_8))) {

                String linea;

                // Lee cada línea que devuelva el comando PowerShell
                // y la escribe en el archivo fecha.txt
                while ((linea = br.readLine()) != null) {

                    if (linea.isBlank()) continue; // Evita guardar lineas en blanco

                    bw.write(linea);
                    bw.newLine(); // añade un salto de línea en el archivo
                }
            }

            // Espera a que el proceso termine completamente
            p.waitFor();

            System.out.println("Escrito correctamente en " + RUTA.toAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

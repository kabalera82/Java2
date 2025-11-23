import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ej07LlamadaArchivo {

    // Ruta al BAT
    private static final Path RUTA_BAT = Path.of(
            "F:/Marcos/Programacion/Java/Java2/ProcessProcessBuilder/data/ejemplo.bat"
    );

    // Ruta al SH
    private static final Path RUTA_SH = Path.of(
            "/home/kabalera/scripts/ejemplo.sh"
    );

    public static void main(String[] args) {
        ejecutarArchivo();
    }

    private static void ejecutarArchivo() {
        try {
            // Detectar SO
            boolean esWindows = System.getProperty("os.name")
                    .toLowerCase()
                    .contains("win");

            // Seleccionar archivo según SO
            Path archivo = esWindows ? RUTA_BAT : RUTA_SH;

            // Construir el proceso
            ProcessBuilder pb = esWindows
                    ? new ProcessBuilder("cmd", "/c", archivo.toString())
                    : new ProcessBuilder("bash", archivo.toString());

            pb.redirectErrorStream(true);

            // Iniciar proceso
            Process p = pb.start();

            // Leer salida del script
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream())
            );

            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            // Esperar finalización
            int codigo = p.waitFor();
            System.out.println("\nScript finalizado con código: " + codigo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Ej16ProcesoConVariable {
    }
}

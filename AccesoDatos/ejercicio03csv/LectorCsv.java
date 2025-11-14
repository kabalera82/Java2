package ejercicio03csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LectorCsv {
    // Ruta relativa hacia el CSV: carpeta 'data' y archivo 'clientesResumen.csv'.
    // Usar Path evita problemas de separadores de ruta entre SO (Windows/Linux/Mac).
   // private static final Path RUTA = Paths.get("data", "clientes.csv");

    private static final Path RUTA = Path.of(
            "F:/Marcos/Programacion/Java/Java2/AccesoDatos/data/clientes.csv"
    );

    public static void main(String[] args) {
        // 1) Asegurar que el directorio donde debería estar el CSV existe.
        //    Si no existe, se crea (createDirectories no falla si ya existe).
        try {
            Files.createDirectories(RUTA.getParent()); // 'data/'
        } catch (IOException e) {
            // Si falla la creación (permisos, disco, etc.) lo notificamos pero seguimos:
            // quizá el directorio ya exista o el CSV esté en otro lugar.
            System.err.println("No se pudo asegurar el directorio: " + e.getMessage());
        }

        // Estructura en memoria para almacenar el contenido del CSV (cabeceras + filas).
        CsvTable table = new CsvTable();

        // 2) Apertura del archivo y lectura línea a línea.
        //    - Files.newBufferedReader: abre un lector eficiente con codificación UTF-8.
        //    - try-with-resources: garantiza el cierre del recurso al finalizar el bloque.
        try (BufferedReader br = Files.newBufferedReader(RUTA, StandardCharsets.UTF_8)) {

            String linea; // Reutilizamos la variable para leer cada línea del CSV.

            // 2.1) Leer la primera línea, que se espera sean las cabeceras.
            linea = br.readLine();

            // Si la primera línea es null, el archivo está vacío (0 bytes o sin contenido legible).
            if (linea == null) {
                System.err.println("CSV VACÍO: no hay cabeceras ni datos.");
                return; // Nada más que hacer; salimos de main.
            }

            // 2.2) Parsear cabeceras.
            //      CsvUtils.parseCSVLine debe convertir "a,b,c" -> ["a","b","c"]
            //      manejando comillas si fuera necesario: "a","b,con,comas","c"
            List<String> headers = CsvUtils.parseCSVLine(linea);

            // 2.3) Cargar las cabeceras en la tabla para que CsvPrinter pueda etiquetar columnas.
            table.setHeaders(headers);

            // 2.4) Leer el resto de líneas hasta EOF.
            //      Cada línea representa una fila de datos con el mismo número de columnas (idealmente).
            while ((linea = br.readLine()) != null) {

                // 2.5) Parsear cada línea en campos individuales según el formato CSV.
                List<String> campos = CsvUtils.parseCSVLine(linea);

                // 2.6) Añadir la fila a la tabla.
                //      Suele ser buena práctica validar longitud: campos.size() == headers.size().
                //      Si CsvTable no lo controla internamente, podría añadirse una comprobación aquí.
                table.addRow(campos);
            }

        } catch (NoSuchFileException e) {
            // Caso específico: el archivo no existe en la ruta esperada.
            // toAbsolutePath() ayuda a diagnosticar mostrando la ruta completa.
            System.err.println("No aparece el archivo: " + RUTA.toAbsolutePath());
            // Sugerencia: ofrecer crear un CSV de ejemplo si procede.
        } catch (IOException e) {
            // Otras IOExceptions: permisos, bloqueo de archivo, encoding corrupto, etc.
            e.printStackTrace();
        }

        // 3) Mostrar el contenido leido en formato tabular.
        //    CsvPrinter.print(table) se encarga del formato (alineación, separadores, etc.).
        //    Si la tabla no tiene filas, idealmente CsvPrinter debería mostrar solo cabeceras
        //    o informar "sin datos".
        CsvPrinter.print(table);

    }
}

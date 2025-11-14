package ejercicio01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que escribe y lee datos de clientes en un archivo de texto plano.
 * <p>
 * Usa {@link BufferedWriter} para guardar la información y {@link BufferedReader} para leerla.
 * Cada cliente se almacena en una línea con los campos separados por punto y coma.
 * </p>
 */
public class TextoPlano {

    /** Ruta del archivo donde se guardan los datos. */
    private static final Path RUTA = Paths.get("data/clientes.txt");

    /**
     * Crea una lista de clientes, los guarda en el archivo y luego los lee y muestra por consola.
     *
     * @param args argumentos de la línea de comandos (no utilizados)
     */
    public static void main(String[] args) {

        // Lista inicial de clientes
        List<Cliente> clientes = List.of(
                new Cliente(1, "Ana", "ana@correo.com", 1200),
                new Cliente(2, "Luis", "luis@correo.com", 950),
                new Cliente(3, "María", "maria@correo.com", 1800),
                new Cliente(4, "Carlos", "carlos@correo.com", 750),
                new Cliente(5, "Elena", "elena@correo.com", 2100),
                new Cliente(6, "Javier", "javier@correo.com", 1300)
        );

        // Escritura en el archivo
        try (BufferedWriter bw = Files.newBufferedWriter(RUTA, StandardCharsets.UTF_8)) {
            for (Cliente c : clientes) {
                bw.write(c.getId() + ";" + c.getNombre() + ";" + c.getEmail() + ";" + c.getSaldo());
                bw.newLine();
            }
            System.out.println("Escrito en " + RUTA.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Lista donde se guardarán los clientes leídos
        List<Cliente> leidos = new ArrayList<>();

        // Lectura del archivo
        try (BufferedReader br = Files.newBufferedReader(RUTA, StandardCharsets.UTF_8)) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";", -1);

                if (partes.length != 4) {
                    System.err.println("Línea con formato incorrecto: " + linea);
                    continue;
                }

                try {
                    int id = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String email = partes[2];
                    double saldo = Double.parseDouble(partes[3]);

                    leidos.add(new Cliente(id, nombre, email, saldo));

                } catch (NumberFormatException ex) {
                    System.err.println("Error numérico en línea: " + linea);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Mostrar los clientes leídos
        System.out.println("Leídos:");
        leidos.forEach(System.out::println);
    }
}

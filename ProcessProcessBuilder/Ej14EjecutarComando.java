import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ej14EjecutarComando {

    /*
    Ejercicio 14 – Clase utilitaria
    Crea una clase llamada ComandoUtils con un método estático ejecutar(String comando)
    que devuelva la salida del comando como un String.
     */

    public static void main(String[] args) {

        ejecutar();

    }


    public static String ejecutar() {


            try {
                // Crea un proceso del sistema operativo:
                // "cmd /c dir" → ejecuta el comando DIR en Windows
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir");

                // Arranca el proceso
                Process proceso = pb.start();

                // Prepara un lector para recoger la salida del proceso (lo que imprime DIR)
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(proceso.getInputStream())
                );

                // Lee línea a línea la salida del proceso y la muestra por consola
                String resultado = "";
                String acumulador = "";
                String linea;
                while ((linea = reader.readLine()) != null) {
                    resultado += linea;
                }


                // Espera a que el proceso termine y captura el código de salida
                int exitCode = proceso.waitFor();
                System.out.println(resultado);

            } catch (Exception e) {
                // Muestra información del error si algo falla
                e.printStackTrace();
            }
        return resultado;

    }
}
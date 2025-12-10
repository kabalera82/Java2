package Tema19ContructorProcesos.src.main.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ejercicio01 {

    public static void main(String[] args) {

        // Stream

        // Binarios -> InputStream / OutputStream -> 0,1

        // InputStream in = process.getInputStream() -> leer la salida de un proceso

        // OutputStream out = process.getOutputStream(); -> enviar comandos a un proceso

        // Texto -> reader / writer

        // ProcessBuilder -> java.lang
        // ProcessBuilder pb = new ProcessBuilder("argumento1", "argumento2");

        // Process
        // Process proceso = pb.start();

        /**
         *  Métodos útiles de ProcessBuilder y Process en Java
         *
         *  ProcessBuilder:
         *
         * directory(File dir)
         *      → Cambia el directorio de trabajo del proceso.
         *
         * environment()
         *      → Devuelve un Map<String, String> con las variables de entorno del proceso.
         *
         * redirectOutput(File file)
         *      → Redirige la salida estándar (System.out) a un fichero.
         *
         * redirectError(File file)
         *      → Redirige la salida de error (System.err) a un fichero.
         *
         * redirectErrorStream(boolean redirect)
         *      → Si es true, combina la salida estándar y la de error en un único flujo.
         *
         * start()
         *      → Lanza el proceso configurado y devuelve un objeto Process.
         *
         *  Process (objeto devuelto por start()):
         *
         * getInputStream()
         *      → Devuelve un InputStream con la salida estándar del proceso.
         *        (lo que normalmente imprimiría en consola).
         *
         * getErrorStream()
         *      → Devuelve un InputStream con la salida de error del proceso.
         *
         * getOutputStream()
         *      → Devuelve un OutputStream para enviar datos al proceso (entrada estándar).
         *
         * waitFor()
         *      → Espera a que el proceso termine y devuelve su código de salida.
         *
         * exitValue()
         *      → Devuelve el código de salida del proceso (0 = éxito).
         *        Lanza IllegalThreadStateException si el proceso aún no ha terminado.
         *
         * destroy()
         *      → Termina (mata) el proceso de forma inmediata.
         *
         * destroyForcibly()
         *      → Mata el proceso incluso si no responde al destroy() normal.
         */
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "cmd",  // Ejecuta el intérprete de comandos de Windows (cmd.exe)
                    "/c",   // /c → ejecuta el comando y cierra
                    "dir"   // Comando que se ejecutará: lista los archivos y carpetas del directorio actual
            );

            Process proceso = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

            String linea;

            // 🔹 Corrección: readLine() devuelve String, no se puede usar forEach
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

            int exitCode = proceso.waitFor();
            System.out.println("Proceso terminado con éxito: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

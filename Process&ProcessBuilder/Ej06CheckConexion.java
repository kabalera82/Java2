import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ej06CheckConexion {

    /*
    Haz un programa que ejecute el comando ping a una dirección (por ejemplo, ping
    google.com) y muestre si la conexión fue exitosa o no en base al código de salida del
    proceso.
     */

    public static void main(String[] args) {
        System.out.println("Inserte una direccion:");
        Scanner sc = new Scanner(System.in);
        String destino = sc.nextLine();
        comprobarConexion(destino);
    }

    private static void comprobarConexion(String destino) {

        try {
            // Detectamos el sistema operativo
            String[] comando = System.getProperty("os.name").toLowerCase().contains("win")
                    ? new String[]{"cmd", "/c", "ping -n 3 " + destino}
                    : new String[]{"bash", "-c", "ping -c 3 " + destino};

            // Creamos el proceso
            ProcessBuilder constructorProceso = new ProcessBuilder(comando);
            // Esto mantiene salida (STDOUT) y error (STDERR) unidos
            constructorProceso.redirectErrorStream(true); // <- en true
            // Iniciamos el proceso
            Process proceso = constructorProceso.start();

            // Leemos la salida ddel comando
            BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
            // espera que el ping termine y devuelve el exitcode
            // aqui se captura si el ping ha sido correcto
            // 0 ✅ 1 ❌ tiempo agotado no responde ❌ de error en el comando o ip (echo %errorlevel%)
            int codigo = proceso.waitFor();

            if (codigo == 0) {
                System.out.println("Conexión exitosa");
            } else {
                System.out.println("Fallo");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

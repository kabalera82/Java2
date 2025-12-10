package Tema18ProgramacionRed.Ejercicio01;

import java.net.URL;

public class Conexion {
    /*
     * Importante crear el objeto URL
     * metodo para acceder protocolo .getProtocol()
     * metodo para acceder a la URL .getHost()
     * metodo para acceder al puerto .getPort()
     * metodo para acceder al archivo .getFile()
     */

    public static void main(String[] args) {

        try {
            URL url1 = new URL("http://www.google.es:80/index.html");
            URL url2 = new URL("http", "www.uji.es", 80, "index.jsp");

            mostrarDatos(url1);
            System.out.println("--------------------------------------------------------------------------");
            mostrarDatos(url2);
            System.out.println("--------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("Error al crear la URL: " + e.getMessage());
        }
    }
    public static void mostrarDatos (URL url){
        System.out.println("Protocolo: " + url.getProtocol()+ "\n"+
                "Host: " + url.getHost() + "\n" +
                "puerto: " + url.getPort() + "\n" +
                "Recurso: " + url.getFile()
                );

    }

}

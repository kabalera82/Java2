package Tema18ProgramacionRed.Ejercicio01.Ejercicio01ConexionLocal;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Servidor {

    public static void main(String[] args) throws IOException {
        HttpServer servidor = HttpServer.create(new InetSocketAddress(8080), 0);
        servidor.createContext("/music", new MusicServer());
        servidor.createContext("/video", new VideoServer());
        servidor.createContext("/images", new ImageServer());

        servidor.start();
        System.out.println("Music server is ON");
        System.out.println("Video server is ON");
        System.out.println("Image server is ON");
    }
}

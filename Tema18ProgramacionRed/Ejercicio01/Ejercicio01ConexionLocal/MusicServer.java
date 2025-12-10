package Tema18ProgramacionRed.Ejercicio01.Ejercicio01ConexionLocal;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class MusicServer implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "AQUI ESTA SONANDO MUSICA";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
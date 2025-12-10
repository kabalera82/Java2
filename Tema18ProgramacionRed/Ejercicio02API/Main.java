package Tema18ProgramacionRed.Ejercicio02API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    //Atributo
    private static String endpoint = "https://ghibliapi.vercel.app/people"; //"https://ghibliapi.vercel.app/films";

    public static void main(String[] args) {
        System.out.println("Calling Ghibli API");
        llamadaAPI();

    }

    private static void llamadaAPI(){
        try {
            System.out.println("Conecting with API");

            URL url = new URL(endpoint);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            int status = con.getResponseCode();

            if (status != 200) {
                System.out.println(" Error HTTP: " + status);
                return;
            }

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            );

            StringBuilder res = new StringBuilder();
            String line;

            while((line = rd.readLine()) != null){
                res.append(line);
            }
            rd.close();
            con.disconnect();

            System.out.println("JSON receibed: \n");
            String json = res.toString()
                    .replace(",", ",\n")
                    .replace("{", "{\n")
                    .replace("}", "\n}");
            System.out.println(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
